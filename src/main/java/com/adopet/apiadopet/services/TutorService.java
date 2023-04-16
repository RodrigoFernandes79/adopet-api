package com.adopet.apiadopet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.DadosEntradaTutor;
import com.adopet.apiadopet.domains.Tutor;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.repositories.TutorRepository;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;

	public Tutor cadastrarTutor(DadosEntradaTutor dadosEntradaTutor) {

		var email = dadosEntradaTutor.email();
		Optional<Tutor> emailExistente = tutorRepository.findByEmail(email);
		if (emailExistente.isPresent()) {

			throw new DadosExistenteException("Email já existe no Banco de Dados.");

		}
		var tutor = new Tutor(dadosEntradaTutor);
		tutorRepository.save(tutor);

		return tutor;
	}

}
