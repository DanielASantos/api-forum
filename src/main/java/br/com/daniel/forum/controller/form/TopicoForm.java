package br.com.daniel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Curso;
import br.com.daniel.forum.modelo.Topico;
import br.com.daniel.forum.repository.CursoRepository;
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
	private String nomeCurso;
	
		public Topico converter(CursoRepository cursoRepository) {
			Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
}
