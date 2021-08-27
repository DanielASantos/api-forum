package br.com.daniel.forum.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Curso {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	private Categoria categoria;
	
	public Curso(String nome, Categoria categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}
}
