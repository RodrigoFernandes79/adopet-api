package com.adopet.apiadopet.domains.pet;

import com.adopet.apiadopet.domains.endereco.Endereco;

public record DadosSaidaPet(Long id, Long abrigo_id, String nome,
 String descricao, Boolean adotado, Endereco endereco, String imagem) {

	public DadosSaidaPet(Pet pet) {
		this(pet.getId(), pet.getAbrigo().getId(),
		 pet.getNome(),pet.getPersonalidade(),pet.getAdotado(),
		 pet.getAbrigo().getEndereco(), pet.getImagem());
	}

}
