package com.adopet.apiadopet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.pet.DadosEntradaPet;
import com.adopet.apiadopet.domains.pet.Pet;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.AbrigoRepository;
import com.adopet.apiadopet.repositories.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private AbrigoRepository abrigoRepository;

	public Pet cadastrarPet(DadosEntradaPet dadosEntradaPet) {

		var abrigo = abrigoRepository.findById(dadosEntradaPet.abrigo_id());
		if (abrigo.isEmpty()) {
			throw new ObjetoNaoEncontrado("Abrigo não existe no Banco de dados.");
		}
		var pet = new Pet(dadosEntradaPet);
		pet.setAbrigo(abrigo.get());
		petRepository.save(pet);
		return pet;
}
}
