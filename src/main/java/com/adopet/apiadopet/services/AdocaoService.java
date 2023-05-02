package com.adopet.apiadopet.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.adocao.Adocao;
import com.adopet.apiadopet.domains.adocao.DadosEntradaAdocao;
import com.adopet.apiadopet.domains.adocao.DadosSaidaAdocao;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.AdocaoRepository;
import com.adopet.apiadopet.repositories.PetRepository;
import com.adopet.apiadopet.repositories.TutorRepository;

@Service
public class AdocaoService {

	@Autowired
	private AdocaoRepository adocaoRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private TutorRepository tutorRepository;

	public DadosSaidaAdocao adotarPet(DadosEntradaAdocao dadosEntradaAdocao) {
		var petEntidade = petRepository.findById(dadosEntradaAdocao.petId());
		if (petEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Pet não encontrado.");
		}
		if (petEntidade.get().getAdotado() == true) {
			throw new DadosExistenteException("Esse pet já foi adotado.");
		}

		var tutorEntidade = tutorRepository.findById(dadosEntradaAdocao.tutorId());
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Tutor não cadastrado.");
		}

		petEntidade.get().setAdotado(true);

		var adocaoEntidade = new Adocao(null, petEntidade.get(),
				tutorEntidade.get(), LocalDateTime.now());
		adocaoRepository.save(adocaoEntidade);

		return new DadosSaidaAdocao(adocaoEntidade);
	}

	public void deletarAdocaoPorPetId(Long id_pet) {
		var petEntidade = petRepository.findById(id_pet);
		if (petEntidade.isEmpty() || petEntidade.get().getAdotado() == false) {
			throw new ObjetoNaoEncontrado(
					"Adoção não pode ser concluida.Pet não foi adotado ou não existe no Banco de dados");
		}
		var adocaoEntidade = adocaoRepository.findByPetId(petEntidade.get().getId());
		if (adocaoEntidade == null) {
			throw new ObjetoNaoEncontrado("Adoção já foi deletada.");
		}
		petEntidade.get().setAdotado(false);
		adocaoRepository.delete(adocaoEntidade);
	}

}
