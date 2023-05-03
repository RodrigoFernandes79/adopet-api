package com.adopet.apiadopet.domains.adocao.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adopet.apiadopet.domains.adocao.DadosEntradaAdocao;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.PetRepository;

@Component
public class ValidadorPetNaoEncontrado implements ValidadorAdotarPet {

	@Autowired
	private PetRepository petRepository;

	public void adotar(DadosEntradaAdocao dadosEntradaAdocao) {
		var petEntidade = petRepository.findById(dadosEntradaAdocao.petId());
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Pet não encontrado.");
		}
		if (petEntidade.get().getAdotado() == true) {
			throw new DadosExistenteException("Esse pet já foi adotado.");
		}
	}
}
