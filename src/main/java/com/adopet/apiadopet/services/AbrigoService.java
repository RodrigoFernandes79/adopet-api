package com.adopet.apiadopet.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopet.apiadopet.domains.abrigo.Abrigo;
import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosListagemAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosSaidaAbrigo;
import com.adopet.apiadopet.exceptions.DadosExistenteException;
import com.adopet.apiadopet.exceptions.ObjetoNaoEncontrado;
import com.adopet.apiadopet.repositories.AbrigoRepository;

@Service
public class AbrigoService {

	@Autowired
	private AbrigoRepository abrigoRepository;

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
		var abrigo = new Abrigo(dadosEntradaAbrigo);
		abrigoRepository.save(abrigo);
		return abrigo;
	}

	public List<DadosListagemAbrigo> listarAbrigo() {
		List<Abrigo> abrigoEntidade = abrigoRepository.findAll();
		if (abrigoEntidade.isEmpty()) {
			throw new ObjetoNaoEncontrado("Não Encontrado");
		}
		List<DadosListagemAbrigo> abrigo = abrigoEntidade.stream()
				.map(DadosListagemAbrigo::new).collect(Collectors.toList());
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

}