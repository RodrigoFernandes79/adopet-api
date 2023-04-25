package com.adopet.apiadopet.domains.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEntradaEndereco(
		@NotBlank(message = "{cidade.obrigatorio}") String cidade,
		@NotBlank(message = "{estado.obrigatorio}") String estado) {

}
