package com.adopet.apiadopet.domains.tutor;

public record DadosSaidaTutor(String nome, String imagem, String email,
		String telefone, String cidade, String estado, String sobre) {

	public DadosSaidaTutor(Tutor tutor) {

		this(tutor.getNome(), tutor.getImagem(), tutor.getEmail(),
				tutor.getTelefone(), tutor.getCidade(), tutor.getEstado(), tutor.getSobre());

	}

}
