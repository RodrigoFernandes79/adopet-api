package com.adopet.apiadopet.domains.tutor;

public record DadosSaidaTutor(String nome, String email) {

	public DadosSaidaTutor(Tutor tutor) {
		this(tutor.getNome(), tutor.getEmail());
	}

}
