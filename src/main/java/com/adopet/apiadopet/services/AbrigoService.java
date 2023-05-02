package com.adopet.apiadopet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.abrigo.Abrigo;
import com.adopet.apiadopet.domains.abrigo.DadosAtualizacaoAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosListagemAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosSaidaAbrigo;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.exceptions.SenhaNaoIgualException;
import com.adopet.apiadopet.repositories.AbrigoRepository;
import com.adopet.apiadopet.security.Usuario;
import com.adopet.apiadopet.security.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class AbrigoService {

	@Autowired
	private AbrigoRepository abrigoRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Abrigo cadastrarAbrigo(DadosEntradaAbrigo dadosEntradaAbrigo) {
		var cnpjAbrigo = dadosEntradaAbrigo.cnpj();
		var email = dadosEntradaAbrigo.email();
		var cnpjExistente = abrigoRepository.findByCnpj(cnpjAbrigo);
		var emailExistente = abrigoRepository.findByEmail(email);
		if (cnpjExistente.isPresent()) {
			throw new DadosExistenteException("CNPJ já existe no BD.");
		}
		if (emailExistente.isPresent()) {
			throw new DadosExistenteException("EMAIL já existe no BD.");
		}
		if (!dadosEntradaAbrigo.senha().equals(dadosEntradaAbrigo.senhaRepetida())) {
			throw new SenhaNaoIgualException("Senhas devem ser iguais.");
		}
		var abrigo = new Abrigo(dadosEntradaAbrigo);
		var gerarSenhaBcryprografada = passwordEncoder.encode(abrigo.getSenha());
		abrigo.setSenha(gerarSenhaBcryprografada);
		abrigoRepository.save(abrigo);

		var usuario = new Usuario(dadosEntradaAbrigo);
		var gerarSenhaBcryprografadaUsuario = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(gerarSenhaBcryprografadaUsuario);
		usuarioRepository.save(usuario);
		return abrigo;
	}

	public Page<DadosListagemAbrigo> listarAbrigo(Pageable pageable) {
		Page<Abrigo> abrigoEntidade = abrigoRepository.findAll(pageable);
		if (abrigoEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não Encontrado");
		}
		Page<DadosListagemAbrigo> abrigo = abrigoEntidade.map(DadosListagemAbrigo::new);

		return abrigo;
	}

	public DadosSaidaAbrigo listarAbrigoPorId(Long id) {
		var abrigoEntidade = abrigoRepository.findById(id);
		if (abrigoEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não encontrado.");
		}
		var abrigo = new DadosSaidaAbrigo(abrigoEntidade.get());
		return abrigo;
	}

	public void deletarAbrigoPorId(Long id) {
		var abrigoEntidade = abrigoRepository.findById(id);
		if (abrigoEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado(
					"Não foi possível apagar o abrigo. não encontrado.");
		}
		abrigoRepository.delete(abrigoEntidade.get());
	}

	public DadosSaidaAbrigo atualizarAbrigoPorId(@Valid DadosAtualizacaoAbrigo dadosAtualizacaoAbrigo, Long id) {
		var abrigoEntidade = abrigoRepository.findById(id);
		if (abrigoEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Abrigo não encontrado.");
		}
		abrigoEntidade.get().dadosAbrigoAtualizado(dadosAtualizacaoAbrigo);
		return new DadosSaidaAbrigo(abrigoEntidade.get());
	}

}
