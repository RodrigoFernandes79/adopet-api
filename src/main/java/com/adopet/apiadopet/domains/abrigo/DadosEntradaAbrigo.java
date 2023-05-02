package com.adopet.apiadopet.domains.abrigo;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEntradaAbrigo(
		@NotBlank(message = "{nome.obrigatorio}") String nome,
		@URL(message = "{imagem.valida}") @NotBlank(message = "{imagem.obrigatorio}")  String imagem,
		@CNPJ(message = "{cnpj.valido}") @NotBlank(message = "{cnpj.obrigatorio}") String cnpj,
		@NotBlank(message = "{telefone.obrigatorio}") String telefone,
		@NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") String email,
		@NotBlank(message="{senha.obrigatoria}")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", message = "{senha.invalida}")
		String senha,
		@NotBlank(message="{senha.obrigatoria}")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", message = "{senha.invalida}")
		String senhaRepetida,
		@NotNull(message = "{endereco.obrigatorio}")
        @Valid DadosEntradaEndereco endereco,
		@NotBlank(message = "{sobre.obrigatorio}") String sobre) {

}
