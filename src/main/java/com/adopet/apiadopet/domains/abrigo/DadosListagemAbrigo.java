package com.adopet.apiadopet.domains.abrigo;

import com.adopet.apiadopet.domains.endereco.Endereco;

public record DadosListagemAbrigo(Long id, String nome, String imagem,
		String cnpj, String telefone, String email, Endereco endereco, String sobre) {

	public DadosListagemAbrigo(Abrigo abrigo) {
		this(abrigo.getId(), abrigo.getNome(), abrigo.getImagem(), abrigo.getCnpj(),
				abrigo.getTelefone(), abrigo.getEmail(), abrigo.getEndereco(), abrigo.getSobre());
	}
}
