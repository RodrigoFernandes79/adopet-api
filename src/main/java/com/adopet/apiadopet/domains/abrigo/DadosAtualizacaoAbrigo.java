package com.adopet.apiadopet.domains.abrigo;

import org.hibernate.validator.constraints.URL;

import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;

import jakarta.validation.Valid;

public record DadosAtualizacaoAbrigo(
		@URL(message = "{imagem.valida}") String imagem,
		String telefone,
		 @Valid DadosEntradaEndereco endereco,
		String sobre) {

}
