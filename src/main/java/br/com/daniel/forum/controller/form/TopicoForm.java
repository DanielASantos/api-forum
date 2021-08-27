package br.com.daniel.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Curso;
import br.com.daniel.forum.modelo.Topico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;
	
	@NotNull @NotEmpty
	private Long idCurso;

	public Topico converter(Optional<Curso> curso) {
		return new Topico(titulo, mensagem, curso.get());
	}
	
}
