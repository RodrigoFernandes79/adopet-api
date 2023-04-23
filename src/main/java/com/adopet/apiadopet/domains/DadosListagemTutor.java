package com.adopet.apiadopet.domains;

public record DadosListagemTutor(Long id, String nome, String imagem,
		String email, String telefone, String cidade, String estado, String sobre) {

	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getImagem(), tutor.getEmail(),
				tutor.getTelefone(), tutor.getCidade(), tutor.getEstado(), tutor.getSobre());
	}

}
