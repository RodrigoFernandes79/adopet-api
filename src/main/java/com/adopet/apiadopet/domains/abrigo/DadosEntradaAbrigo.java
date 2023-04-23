package com.adopet.apiadopet.domains.abrigo;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosEntradaAbrigo(
		@NotBlank(message = "{nome.obrigatorio}") String nome,
		@URL(message = "{imagem.valida}") String imagem,
		@CNPJ(message = "{cnpj.valido}") @NotBlank(message = "{cnpj.obrigatorio}") String cnpj,
		@NotBlank(message = "{telefone.obrigatorio}") String telefone,
		@NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") String email,
		@NotBlank(message = "{cidade.obrigatorio}") String cidade,
		@NotBlank(message = "{estado.obrigatorio}") String estado,
		String sobre) {

}
