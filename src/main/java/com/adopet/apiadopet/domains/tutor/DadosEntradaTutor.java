package com.adopet.apiadopet.domains.tutor;

import org.hibernate.validator.constraints.URL;

import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEntradaTutor(

		@NotBlank(message = "{nome.obrigatorio}") String nome,
		@URL(message = "{imagem.valida}") String imagem,
		@NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") String email,
		String telefone,
		@NotNull(message = "{endereco.obrigatorio}") @Valid DadosEntradaEndereco endereco,
		String sobre) {

}
