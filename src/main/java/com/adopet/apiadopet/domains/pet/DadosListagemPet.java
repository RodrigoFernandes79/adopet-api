package com.adopet.apiadopet.domains.pet;

import com.adopet.apiadopet.domains.endereco.Endereco;
import com.adopet.apiadopet.domains.pet.enums.Tamanho;

public record DadosListagemPet(Long id, String nome, String idade, Tamanho tamanho,
		String imagem, String personalidade, Endereco endereco) {

	public DadosListagemPet(Pet pet) {
		this(pet.getId(), pet.getNome(), pet.getIdade(), pet.getTamanho(),
				pet.getImagem(), pet.getPersonalidade(), pet.getAbrigo().getEndereco());
	}

}
