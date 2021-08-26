package br.com.daniel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.daniel.forum.modelo.Topico;
import br.com.daniel.forum.repository.TopicoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoTopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;

	public Topico atualizar(Topico topico, TopicoRepository topicoRepository) {
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
}
