package com.adopet.apiadopet.domains.abrigo;

public record DadosSaidaAbrigo(
		String nome, String imagem, String email,
		String telefone, String cnpj, String cidade,
		String estado, String sobre) {

	public DadosSaidaAbrigo(Abrigo abrigo) {
		this(abrigo.getNome(), abrigo.getImagem(), abrigo.getEmail(),
				abrigo.getTelefone(), abrigo.getCnpj(), abrigo.getCidade(),
				abrigo.getEstado(), abrigo.getSobre());
	}

}
