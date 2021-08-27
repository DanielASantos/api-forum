package br.com.daniel.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.daniel.forum.modelo.Categoria;
import br.com.daniel.forum.modelo.Curso;
import lombok.Getter;

@Getter
public class CursoDto {
	
	private Long id;
	private String nome;
	private String categoria;
	
	public CursoDto(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria().getNome();
	}

	public static Page<CursoDto> converter(Page<Curso> cursos) {
		return cursos.map(CursoDto::new);
	}

}
