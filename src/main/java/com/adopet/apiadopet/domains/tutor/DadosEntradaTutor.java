package com.adopet.apiadopet.domains.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEntradaTutor(

		@NotBlank(message = "{nome.obrigatorio}") String nome,
		@NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}")
		String email,

		@NotBlank(message = "{senha.obrigatoria}")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", message = "{senha.invalida}")
		String senha,
		@NotBlank(message = "{senha.obrigatoria}")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", message = "{senha.invalida}")
		String senhaRepetida) {

}
