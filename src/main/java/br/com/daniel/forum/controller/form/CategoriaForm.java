package br.com.daniel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Categoria;
import br.com.daniel.forum.repository.CategoriaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaForm {

	@NotNull @NotEmpty @Length(min = 5, max = 20)
	private String nome;

	public Categoria converter() {
		return new Categoria(nome);
	}

	public Categoria atualizar(Categoria categoria, CategoriaRepository categoriaRepository) {
		categoria.setNome(this.nome);
		return categoria;
	}
}
