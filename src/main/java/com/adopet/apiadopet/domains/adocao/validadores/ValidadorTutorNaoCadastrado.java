package com.adopet.apiadopet.domains.adocao.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adopet.apiadopet.domains.adocao.DadosEntradaAdocao;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.TutorRepository;

@Component
public class ValidadorTutorNaoCadastrado implements ValidadorAdotarPet {

	@Autowired
	private TutorRepository tutorRepository;

	public void adotar(DadosEntradaAdocao dadosEntradaAdocao) {
		var tutorEntidade = tutorRepository.findById(dadosEntradaAdocao.tutorId());
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Tutor não cadastrado.");
		}
	}
}
