package br.com.daniel.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.daniel.forum.modelo.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

}
