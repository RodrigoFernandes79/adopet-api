package com.adopet.apiadopet.domains.perfil;

public enum Perfil {
	ROLE_TUTOR("ROLE_TUTOR"),
	ROLE_ABRIGO("ROLE_ABRIGO");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao =descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
