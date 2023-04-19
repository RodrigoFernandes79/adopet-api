package com.adopet.apiadopet.domains;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTutor(

		@NotBlank(message = "{email.obrigatorio}")
	  @Email(message = "{email.invalido}")
	  String email) {

}
