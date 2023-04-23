package com.adopet.apiadopet.domains.tutor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tutor")
@Table(name = "tutores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private String email;
	private String telefone;
	private String cidade;
	private String estado;
	@Column(columnDefinition = "TEXT")
	private String sobre;

	public Tutor(DadosEntradaTutor dadosEntradaTutor) {
		this.nome = dadosEntradaTutor.nome();
		this.imagem = dadosEntradaTutor.imagem();
		this.email = dadosEntradaTutor.email();
		this.telefone = dadosEntradaTutor.telefone();
		this.cidade = dadosEntradaTutor.cidade();
		this.estado = dadosEntradaTutor.estado();
		this.sobre = dadosEntradaTutor.sobre();
	}

}