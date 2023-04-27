package com.adopet.apiadopet.domains.pet;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.AssertTrue;

public record DadosAtualizacaoPet(
		String idade,
		@URL(message = "{imagem.valida}") String imagem,
		@AssertTrue(message = "{booleano.true}") Boolean adotado) {

	public DadosAtualizacaoPet(Pet pet) {
		this(pet.getIdade(), pet.getImagem(), pet.getAdotado());
	}

}
