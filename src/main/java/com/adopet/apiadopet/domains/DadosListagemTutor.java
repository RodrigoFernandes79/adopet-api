package com.adopet.apiadopet.domains;

public record DadosListagemTutor(Long id, String nome, String email) {

	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getEmail());
	}

}
