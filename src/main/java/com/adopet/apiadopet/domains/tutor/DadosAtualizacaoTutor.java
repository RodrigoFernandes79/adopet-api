package com.adopet.apiadopet.domains.tutor;

import org.hibernate.validator.constraints.URL;

import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTutor(

		@Email(message = "{email.invalido}") String email,
		@URL(message = "{imagem.valida}") String imagem,
		String telefone,
		@NotNull(message = "{endereco.obrigatorio}") @Valid DadosEntradaEndereco endereco,
		String estado,
		String sobre) {

}
