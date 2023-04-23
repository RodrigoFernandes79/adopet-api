package com.adopet.apiadopet.domains.tutor;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosEntradaTutor(

		@NotBlank(message = "{nome.obrigatorio}") String nome,
		@URL(message = "{imagem.valida}") String imagem,
		@NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") String email,
		String telefone,
		@NotBlank(message = "{cidade.obrigatorio}") String cidade,
		@NotBlank(message = "{estado.obrigatorio}") String estado,
		String sobre) {

}
