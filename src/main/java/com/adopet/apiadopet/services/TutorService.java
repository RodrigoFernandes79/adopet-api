package com.adopet.apiadopet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.DadosAtualizacaoTutor;
import com.adopet.apiadopet.domains.DadosEntradaTutor;
import com.adopet.apiadopet.domains.DadosListagemTutor;
import com.adopet.apiadopet.domains.DadosSaidaTutor;
import com.adopet.apiadopet.domains.Tutor;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.TutorRepository;

import jakarta.validation.Valid;

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

	public List<DadosListagemTutor> retornarTutores() {
		List<Tutor> tutoresEntidade = tutorRepository.findAll();
		if (tutoresEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		List<DadosListagemTutor> tutores = tutoresEntidade.stream()
				.map(DadosListagemTutor::new)
				.collect(Collectors.toList());
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

		var novoEmail = dadosAtualizacaoTutor.email();
		Optional<Tutor> tutorExistente = tutorRepository.findByEmail(novoEmail);
		if (tutorExistente.isPresent()) {
			throw new DadosExistenteException("Email já existe no banco de dados");
		}

		return tutorRepository.findById(id).map(obj -> {
			obj.getId();
			if (dadosAtualizacaoTutor.imagem() != null) {
				obj.setImagem(dadosAtualizacaoTutor.imagem());
			}
			if (novoEmail != null) {
				obj.setEmail(novoEmail);
			}
			if (dadosAtualizacaoTutor.telefone() != null) {
				obj.setTelefone(dadosAtualizacaoTutor.telefone());
			}
			if (dadosAtualizacaoTutor.cidade() != null) {
				obj.setCidade(dadosAtualizacaoTutor.cidade());
			}
			if (dadosAtualizacaoTutor.estado() != null) {
				obj.setEstado(dadosAtualizacaoTutor.estado());
			}
			if (dadosAtualizacaoTutor.sobre() != null) {
				obj.setSobre(dadosAtualizacaoTutor.sobre());
			}
			tutorRepository.save(obj);

			var tutorSaida = new DadosSaidaTutor(obj);
			return tutorSaida;
		}).orElseThrow(() -> new ObjetoNaoEncontrado("Tutor não existe no banco de dados"));

	}

}
