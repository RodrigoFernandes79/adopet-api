package com.adopet.apiadopet.domains.abrigo;

public record DadosListagemAbrigo(Long id, String nome, String imagem,
		String cnpj, String telefone, String email, String cidade, String estado, String sobre) {

	public DadosListagemAbrigo(Abrigo abrigo) {
		this(abrigo.getId(), abrigo.getNome(), abrigo.getImagem(), abrigo.getCnpj(),
				abrigo.getTelefone(), abrigo.getEmail(), abrigo.getCidade(),
				abrigo.getEstado(), abrigo.getSobre());
	}
}
