package com.adopet.apiadopet.domains.pet;

import com.adopet.apiadopet.domains.abrigo.Abrigo;
import com.adopet.apiadopet.domains.pet.enums.Categoria;
import com.adopet.apiadopet.domains.pet.enums.Tamanho;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pet")
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private String idade;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private String personalidade;

	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;

	private Boolean adotado;

	@ManyToOne
	@JoinColumn(name = "abrigo_id")
	private Abrigo abrigo;

	public Pet(DadosEntradaPet dadosEntradaPet) {
		this.nome = dadosEntradaPet.nome();
		this.idade = dadosEntradaPet.idade();
		this.imagem = dadosEntradaPet.imagem();
		this.categoria = dadosEntradaPet.categoria();
		this.personalidade = dadosEntradaPet.personalidade();
		this.tamanho = dadosEntradaPet.tamanho();
		this.adotado = false;

	}

	public void atualizarCadastroPet(DadosAtualizacaoPet dadosAtualizacaoPet) {
		if (dadosAtualizacaoPet.idade() != null) {
			this.idade = dadosAtualizacaoPet.idade();
		}
		if (dadosAtualizacaoPet.imagem() != null) {
			this.imagem = dadosAtualizacaoPet.imagem();
		}
		if (dadosAtualizacaoPet.adotado() != null) {
			this.adotado = dadosAtualizacaoPet.adotado();
		}
	}

}
