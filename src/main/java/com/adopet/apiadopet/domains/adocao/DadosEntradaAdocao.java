package com.adopet.apiadopet.domains.adocao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record DadosEntradaAdocao(
		@NotNull Long petId,
		@NotNull Long tutorId,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {

}
