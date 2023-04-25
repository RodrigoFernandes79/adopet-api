package com.adopet.apiadopet.domains.pet;

import org.hibernate.validator.constraints.URL;

import com.adopet.apiadopet.domains.pet.enums.Categoria;
import com.adopet.apiadopet.domains.pet.enums.Tamanho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEntradaPet(
		@NotBlank(message = "nome.obrigatorio") String nome,
		@URL(message = "{imagem.valida}") @NotBlank(message = "{imagem.obrigatorio}") String imagem,
		@NotBlank(message = "{idade.obrigatoria}") String idade,
		@NotNull(message = "{categoria.obrigatoria}") Categoria categoria,
		@NotNull(message = "{tamanho.obrigatorio}") Tamanho tamanho,
		@NotNull(message = "{personalidade.obrigatoria}") String personalidade,
		@NotNull(message = "{abrigo.obrigatorio}") Long abrigo_id) {

}
