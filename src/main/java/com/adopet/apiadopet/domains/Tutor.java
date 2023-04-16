package com.adopet.apiadopet.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Tutor")
@Table(name = "tutores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;


	public Tutor(DadosEntradaTutor dadosEntradaTutor) {
		this.nome = dadosEntradaTutor.nome();
		this.email = dadosEntradaTutor.email();
		this.senha = dadosEntradaTutor.senha();
	}



}
