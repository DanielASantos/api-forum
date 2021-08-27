package br.com.daniel.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.daniel.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

	Page<Curso> findByNome(String nomeCurso, Pageable paginacao);


}
