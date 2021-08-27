package br.com.daniel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Curso;
import br.com.daniel.forum.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoCursoForm {

	@NotNull @NotEmpty @Length(min = 5, max = 20)
	private String nome;
	
	public Curso atualizar(Curso curso, CursoRepository cursoRepository) {
		curso.setNome(this.nome);
		return curso;
	}
}
