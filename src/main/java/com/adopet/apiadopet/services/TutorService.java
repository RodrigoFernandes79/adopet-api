package com.adopet.apiadopet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.tutor.DadosAtualizacaoTutor;
import com.adopet.apiadopet.domains.tutor.DadosEntradaTutor;
import com.adopet.apiadopet.domains.tutor.DadosListagemTutor;
import com.adopet.apiadopet.domains.tutor.DadosSaidaCadastroTutor;
import com.adopet.apiadopet.domains.tutor.Tutor;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.exceptions.SenhaNaoIgualException;
import com.adopet.apiadopet.repositories.TutorRepository;
import com.adopet.apiadopet.security.Usuario;
import com.adopet.apiadopet.security.UsuarioRepository;

import jakarta.validation.Valid;


@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Tutor cadastrarTutor(DadosEntradaTutor dadosEntradaTutor) {

		var email = dadosEntradaTutor.email();
		Optional<Tutor> emailExistente = tutorRepository.findByEmail(email);
		if (emailExistente.isPresent()) {
			throw new DadosExistenteException("Email já existe no Banco de Dados.");
		}
		if (!dadosEntradaTutor.senha().equals(dadosEntradaTutor.senhaRepetida())) {
			throw new SenhaNaoIgualException("Senhas devem ser iguais");
		}

		var tutor = new Tutor(dadosEntradaTutor);
		var gerarSenhaBcryprografada = passwordEncoder.encode(tutor.getSenha());
		tutor.setSenha(gerarSenhaBcryprografada);

		tutorRepository.save(tutor);

		var usuario = new Usuario(dadosEntradaTutor);
		var gerarSenhaBcryprografadaUsuario = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(gerarSenhaBcryprografadaUsuario);
		usuarioRepository.save(usuario);

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

	public DadosSaidaCadastroTutor retornarTutorPorId(Long id) {
		var tutorEntidade = tutorRepository.findById(id);
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado");
		}
		var tutor = new DadosSaidaCadastroTutor(tutorEntidade.get());
		return tutor;
	}

	public void deletarTutorPorid(Long id) {
		var tutorEntidade = tutorRepository.findById(id);
		if (tutorEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Tutor não existe no banco de dados");
		}
		tutorRepository.delete(tutorEntidade.get());
	}

	public DadosSaidaCadastroTutor alterarTutorPorId(Long id,
			@Valid DadosAtualizacaoTutor dadosAtualizacaoTutor) {
		var tutorEntidade = tutorRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Tutor não existe no banco de dados"));

		tutorEntidade.dadosAbrigoAtualizado(dadosAtualizacaoTutor);

		return new DadosSaidaCadastroTutor(tutorEntidade);

	}

}
