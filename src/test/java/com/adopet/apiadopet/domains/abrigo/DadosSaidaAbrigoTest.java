package com.adopet.apiadopet.domains.abrigo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;

import lombok.var;

public class DadosSaidaAbrigoTest {
	private final String NOME = "FULANO";
	private final String IMAGEM = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeyZAyJNYzHM71aZGvTEA36SOpXro_zVGGng&usqp=CAU";
	private final String EMAIL = "fulano@email.com";
	private final String TELEFONE = "998852474";
	private final String SENHA = "R4f43l!!!";
	private final String SENHAREPETIDA = "R4f43l!!!";
	private final String CNPJ = "00.394.460/0001-41";
	private final DadosEntradaEndereco ENDERECO = new DadosEntradaEndereco("QUIXABÁ", "PE");
	private final String SOBRE = "ADOTE UM CAO";

	@Test
	void DeveriaConverterAbrigoEmDadosSaidaAbrigo() {
		var dadosEntradaAbrigo = new DadosEntradaAbrigo(NOME, IMAGEM, CNPJ, TELEFONE, EMAIL, SENHA, SENHAREPETIDA, ENDERECO,
				SOBRE);
		var abrigo = new Abrigo(dadosEntradaAbrigo);
		var dadosSaida = new DadosSaidaAbrigo(abrigo);

		assertEquals(NOME, dadosSaida.nome());
		assertEquals(IMAGEM, dadosSaida.imagem());
		assertEquals(CNPJ, dadosSaida.cnpj());
		assertEquals(TELEFONE, dadosSaida.telefone());
		assertEquals(EMAIL, dadosSaida.email());
		assertEquals(ENDERECO.cidade(), dadosSaida.endereco().getCidade());
		assertEquals(ENDERECO.estado(), dadosSaida.endereco().getEstado());
		assertEquals(SOBRE, dadosSaida.sobre());

	}
}
