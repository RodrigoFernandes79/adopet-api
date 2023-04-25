package com.adopet.apiadopet.domains.abrigo;

import com.adopet.apiadopet.domains.endereco.Endereco;

public record DadosSaidaAbrigo(
		String nome, String imagem, String email,
		String telefone, String cnpj, Endereco endereco, String sobre) {

	public DadosSaidaAbrigo(Abrigo abrigo) {
		this(abrigo.getNome(), abrigo.getImagem(), abrigo.getEmail(),
				abrigo.getTelefone(), abrigo.getCnpj(),
				abrigo.getEndereco(), abrigo.getSobre());
	}

}
