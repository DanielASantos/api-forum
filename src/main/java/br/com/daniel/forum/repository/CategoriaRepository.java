package br.com.daniel.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.daniel.forum.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{


}
