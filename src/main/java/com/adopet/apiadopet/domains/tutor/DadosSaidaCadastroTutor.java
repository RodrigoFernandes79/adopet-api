package com.adopet.apiadopet.domains.tutor;

import com.adopet.apiadopet.domains.endereco.Endereco;

public record DadosSaidaCadastroTutor(String nome, String imagem, String email,
		String telefone, Endereco endereco, String sobre) {

	public DadosSaidaCadastroTutor(Tutor tutor) {

		this(tutor.getNome(), tutor.getImagem(), tutor.getEmail(),
				tutor.getTelefone(), tutor.getEndereco(), tutor.getSobre());

	}

}
