package br.com.daniel.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.daniel.forum.modelo.Categoria;
import lombok.Getter;

@Getter
public class CategoriaDto {

	private Long id;
	private String nome;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
		return categorias.map(CategoriaDto::new);
	}
}
