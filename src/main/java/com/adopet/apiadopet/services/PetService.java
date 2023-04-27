package com.adopet.apiadopet.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.pet.DadosEntradaPet;
import com.adopet.apiadopet.domains.pet.DadosListagemPet;
import com.adopet.apiadopet.domains.pet.DadosSaidaPet;
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

	public List<DadosListagemPet> listarPets() {
		var petEntidade = petRepository.findAll();
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado.");
		}
		List<DadosListagemPet> pets = petEntidade.stream().map(DadosListagemPet::new)
				.collect(Collectors.toList());
		return pets;
	}

	public DadosSaidaPet listarPetPorId(Long id) {
		var petEntidade = petRepository.findById(id);
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado.");
		}
		var pet = new DadosSaidaPet(petEntidade.get());
		return pet;
	}
}
