package br.com.daniel.forum.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.forum.controller.form.RespostaForm;
import br.com.daniel.forum.modelo.Topico;
import br.com.daniel.forum.repository.RespostaRepository;
import br.com.daniel.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos/resposta")
public class RespostaController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid RespostaForm form) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			
		}
		return ResponseEntity.notFound().build();
	}
}
