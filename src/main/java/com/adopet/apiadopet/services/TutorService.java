package com.adopet.apiadopet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.DadosEntradaTutor;
import com.adopet.apiadopet.domains.DadosSaidaTutor;
import com.adopet.apiadopet.domains.Tutor;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
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

	public List<DadosSaidaTutor> retornarTutores() {
		List<Tutor> tutoresEntidade = tutorRepository.findAll();
		if(tutoresEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		List<DadosSaidaTutor> tutores = tutoresEntidade.stream()
				.map(DadosSaidaTutor::new)
				.collect(Collectors.toList());
		return tutores;
	}

	public DadosSaidaTutor retornarTutorPorId(Long id) {
		var tutorEntidade = tutorRepository.findById(id);
		if(tutorEntidade.isEmpty()){
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		var tutor = new DadosSaidaTutor(tutorEntidade.get());
		return tutor;
	}

}
