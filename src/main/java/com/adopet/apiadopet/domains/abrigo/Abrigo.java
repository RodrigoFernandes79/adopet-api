package com.adopet.apiadopet.domains.abrigo;

import java.util.ArrayList;
import java.util.List;

import com.adopet.apiadopet.domains.pet.Pet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Abrigo")
@Table(name = "abrigos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Abrigo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private String cnpj;
	private String telefone;
	private String email;
	private String cidade;
	private String estado;
	@Column(columnDefinition = "TEXT")
	private String sobre;

	@OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
	private List<Pet> pets = new ArrayList<>();

	public Abrigo(DadosEntradaAbrigo dadosEntradaAbrigo) {
		this.nome = dadosEntradaAbrigo.nome();
		this.imagem = dadosEntradaAbrigo.imagem();
		this.cnpj = dadosEntradaAbrigo.cnpj();
		this.telefone = dadosEntradaAbrigo.telefone();
		this.email = dadosEntradaAbrigo.email();
		this.cidade = dadosEntradaAbrigo.cidade();
		this.estado = dadosEntradaAbrigo.estado();
		this.sobre = dadosEntradaAbrigo.sobre();

	}

	public void dadosAbrigoAtualizado(DadosAtualizacaoAbrigo dadosAtualizacao) {
		if (dadosAtualizacao.imagem() != null) {
			this.imagem = dadosAtualizacao.imagem();
		}
		if (dadosAtualizacao.email() != null) {
			this.email = dadosAtualizacao.email();
		}
		if (dadosAtualizacao.telefone() != null) {
			this.telefone = dadosAtualizacao.telefone();
		}
		if (dadosAtualizacao.cidade() != null) {
			this.cidade = dadosAtualizacao.cidade();
		}
		if (dadosAtualizacao.estado() != null) {
			this.estado = dadosAtualizacao.estado();
		}
		if (dadosAtualizacao.sobre() != null) {
			this.sobre = dadosAtualizacao.sobre();
		}
	}

}
