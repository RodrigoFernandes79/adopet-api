package com.adopet.apiadopet.domains.tutor;

import com.adopet.apiadopet.domains.endereco.Endereco;

public record DadosListagemTutor(Long id, String nome, String imagem,
		String email, String telefone, Endereco endereco, String sobre) {

	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getImagem(), tutor.getEmail(),
				tutor.getTelefone(), tutor.getEndereco(), tutor.getSobre());
	}

}
