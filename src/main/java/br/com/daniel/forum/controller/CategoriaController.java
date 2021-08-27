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

import br.com.daniel.forum.controller.dto.CategoriaDto;
import br.com.daniel.forum.controller.form.CategoriaForm;
import br.com.daniel.forum.modelo.Categoria;
import br.com.daniel.forum.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	@Cacheable(value = "listaDeCategoria")
	public Page<CategoriaDto> listar(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
		return CategoriaDto.converter(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(categoria.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCategoria", allEntries = true)
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.converter();
		categoriaRepository.save(categoria);

		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategoria", allEntries = true)
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaForm form) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			Categoria categoria = form.atualizar(optional.get(), categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaDeCategoria", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
