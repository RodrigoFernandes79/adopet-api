package com.adopet.apiadopet.domains.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String cidade;
	private String estado;

	public Endereco(DadosEntradaEndereco dadosEntradaEndereco) {
		this.cidade = dadosEntradaEndereco.cidade();
		this.estado = dadosEntradaEndereco.estado();

	}

	public void dadosEnderecoAtualizado(DadosEntradaEndereco dadosEndereco) {
		if (dadosEndereco.cidade() != null) {
			this.cidade = dadosEndereco.cidade();
		}
		if (dadosEndereco.estado() != null) {
			this.estado = dadosEndereco.estado();
		}
	}
}
