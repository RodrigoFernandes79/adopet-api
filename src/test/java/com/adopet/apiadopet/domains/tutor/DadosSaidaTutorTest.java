package com.adopet.apiadopet.domains.tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DadosSaidaTutorTest {
	private final String NOME = "FULANO";
	private final String EMAIL = "fulano@email,com";
	private final String SENHA = "R4f43llll!";
	private final String SENHAREPETIDA = "R4f43llll!";

	@Test
	void DeveriaConverterTutorEmDadosSaidaTutor() {
		var dadosEntradaTutor = new DadosEntradaTutor(NOME, EMAIL, SENHA, SENHAREPETIDA);
		var tutor = new Tutor(dadosEntradaTutor);

		var dadosSaidaTutor = new DadosSaidaTutor(tutor);

		assertEquals(NOME, dadosSaidaTutor.nome());
		assertEquals(EMAIL, dadosSaidaTutor.email());

	}
}
