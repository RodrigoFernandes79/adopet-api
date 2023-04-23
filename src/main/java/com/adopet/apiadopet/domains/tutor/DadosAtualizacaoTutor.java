package com.adopet.apiadopet.domains.tutor;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;

public record DadosAtualizacaoTutor(

		@Email(message = "{email.invalido}") String email,
		@URL(message = "{imagem.valida}") String imagem,
		String telefone,
		String cidade,
		String estado,
		String sobre) {

}
