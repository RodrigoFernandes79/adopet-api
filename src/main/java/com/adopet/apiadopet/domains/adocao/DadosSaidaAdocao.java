package com.adopet.apiadopet.domains.adocao;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosSaidaAdocao(
		UUID id,
		Long animal,
		Long tutor,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {

	public DadosSaidaAdocao(Adocao adocao) {
		this(adocao.getId(), adocao.getPet().getId(),
				adocao.getTutor().getId(), adocao.getData());
	}
}
