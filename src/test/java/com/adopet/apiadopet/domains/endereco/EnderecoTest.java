package com.adopet.apiadopet.domains.endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnderecoTest {

	@Test
	void DeveriaConverterDadosEntradaEnderecoEmEndereco() {
		var dadosEntradaEndereco = new DadosEntradaEndereco("Quixabá", "PE");

		var endereco = new Endereco(dadosEntradaEndereco);

		assertEquals(dadosEntradaEndereco.cidade(), endereco.getCidade());
		assertEquals(dadosEntradaEndereco.estado(), endereco.getEstado());
	}
}
