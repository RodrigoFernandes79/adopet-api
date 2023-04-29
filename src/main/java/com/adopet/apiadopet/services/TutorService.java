package com.adopet.apiadopet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.tutor.DadosAtualizacaoTutor;
import com.adopet.apiadopet.domains.tutor.DadosEntradaTutor;
import com.adopet.apiadopet.domains.tutor.DadosListagemTutor;
import com.adopet.apiadopet.domains.tutor.DadosSaidaTutor;
import com.adopet.apiadopet.domains.tutor.Tutor;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.TutorRepository;

import jakarta.validation.Valid;
import lombok.var;

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

	public Page<DadosListagemTutor> retornarTutores(Pageable paginacao) {
		Page<Tutor> tutoresEntidade = tutorRepository.findAll(paginacao);
		if (tutoresEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		Page<DadosListagemTutor> tutores = tutoresEntidade
				.map(DadosListagemTutor::new);

		return tutores;
	}

	public DadosSaidaTutor retornarTutorPorId(Long id) {
		var tutorEntidade = tutorRepository.findById(id);
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		var tutor = new DadosSaidaTutor(tutorEntidade.get());
		return tutor;
	}

	public void deletarTutorPorid(Long id) {
		var tutorEntidade = tutorRepository.findById(id);
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Tutor não existe no banco de dados");
		}
		tutorRepository.delete(tutorEntidade.get());
	}

	public DadosSaidaTutor alterarTutorPorId(Long id,
			@Valid DadosAtualizacaoTutor dadosAtualizacaoTutor) {
		var tutorEntidade = tutorRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Tutor não existe no banco de dados"));

		var novoEmail = dadosAtualizacaoTutor.email();
		Optional<Tutor> tutorExistente = tutorRepository.findByEmail(novoEmail);
		if (tutorExistente.isPresent()) {
			throw new DadosExistenteException("Email já existe no banco de dados");
		}

		tutorEntidade.dadosAbrigoAtualizado(dadosAtualizacaoTutor);

		return new DadosSaidaTutor(tutorEntidade);

	}

}
