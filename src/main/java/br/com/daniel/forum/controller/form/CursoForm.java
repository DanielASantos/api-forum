package br.com.daniel.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Categoria;
import br.com.daniel.forum.modelo.Curso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoForm {

	@NotNull @NotEmpty @Length(min = 5, max = 20)
	private String nome;
	@NotNull
	private Long categoria;
	
	public Curso converter(Optional<Categoria> categoria) {
		return new Curso(nome, categoria.get());
	}
}
