package br.com.daniel.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.daniel.forum.controller.dto.CursoDto;
import br.com.daniel.forum.controller.form.AtualizacaoCursoForm;
import br.com.daniel.forum.controller.form.CursoForm;
import br.com.daniel.forum.modelo.Categoria;
import br.com.daniel.forum.modelo.Curso;
import br.com.daniel.forum.repository.CategoriaRepository;
import br.com.daniel.forum.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	@Cacheable(value = "listaDeCursos")
	public Page<CursoDto> listar(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Curso> cursos = cursoRepository.findAll(paginacao);
		return CursoDto.converter(cursos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			return ResponseEntity.ok(new CursoDto(curso.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCursos", allEntries = true)
	public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoForm form, UriComponentsBuilder uriBuilder) {
		Optional<Categoria> categoria = categoriaRepository.findById(form.getCategoria());
		if (categoria.isPresent()) {
			Curso curso = form.converter(categoria);
			cursoRepository.save(curso);
	
			URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
			return ResponseEntity.created(uri).body(new CursoDto(curso));
		} 
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCursos", allEntries = true)
	public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCursoForm form) {
		Optional<Curso> optional = cursoRepository.findById(id);
		if (optional.isPresent()) {
			Curso curso = form.atualizar(optional.get(), cursoRepository);
			return ResponseEntity.ok(new CursoDto(curso));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCursos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			cursoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
