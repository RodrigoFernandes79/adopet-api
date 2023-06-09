package com.adopet.apiadopet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.pet.DadosAtualizacaoPet;
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

	public Page<DadosListagemPet> listarPets(Pageable pageable) {
		var petEntidade = petRepository.findAllByAdotadoFalse(pageable);
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado.");
		}
		Page<DadosListagemPet> pets = petEntidade.map(DadosListagemPet::new);
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

	public void deletarPetPorId(Long id) {
		var pet = petRepository.findById(id);
		if (pet.isEmpty() || pet.get().getAdotado() == true) {
			throw new ObjetoNaoEncontrado("Não foi possível deletar.Pet não encontrado ou já foi adotado.");
		}
		petRepository.delete(pet.get());
	}

	public DadosSaidaPet atualizarPetPorId(Long id, DadosAtualizacaoPet dadosAtualizacaoPet) {
		var petEntidade = petRepository.findById(id);
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Pet não encontrado.");
		}

		petEntidade.get().atualizarCadastroPet(dadosAtualizacaoPet);
		return new DadosSaidaPet(petEntidade.get());
	}
}
