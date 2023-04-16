package com.adopet.apiadopet.domains;

public record DadosSaidaTutor(String nome, String email) {

	public DadosSaidaTutor(Tutor tutor) {

		this(tutor.getNome(), tutor.getEmail());

	}

}
