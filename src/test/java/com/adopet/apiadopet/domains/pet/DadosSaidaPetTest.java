package com.adopet.apiadopet.domains.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.adopet.apiadopet.domains.abrigo.Abrigo;
import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;
import com.adopet.apiadopet.domains.pet.enums.Categoria;
import com.adopet.apiadopet.domains.pet.enums.Tamanho;

public class DadosSaidaPetTest {

	private Long ABRIGO_ID = 1L;
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
	void DeveriaConverterPetEmDadosSaidaPet() {
		// Criar objeto Abrigo
		var dadosEntradaAbrigo = new DadosEntradaAbrigo(NOME, IMAGEM, CNPJ, TELEFONE, EMAIL, SENHA, SENHAREPETIDA, ENDERECO,
				SOBRE);
		var abrigo = new Abrigo(dadosEntradaAbrigo);
		// Criar objeto Pet e associá-lo ao Abrigo
		Pet pet = new Pet(new DadosEntradaPet("nome", "http://imagem.com", "10", Categoria.CACHORRO, Tamanho.PORTE_MEDIO,
				"personalidade", ABRIGO_ID));
		pet.setId(1L);
		pet.setAbrigo(abrigo);

		// Converter o objeto Pet em um objeto DadosSaidaPet
		DadosSaidaPet dadosSaidaPet = new DadosSaidaPet(pet);

		// Verificar se os dados convertidos estão corretos
		assertEquals(pet.getId(), dadosSaidaPet.id());
		assertEquals(pet.getNome(), dadosSaidaPet.nome());
		assertEquals(pet.getPersonalidade(), dadosSaidaPet.descricao());
		assertEquals(pet.getIdade(), dadosSaidaPet.idade());
		assertEquals(pet.getAdotado(), dadosSaidaPet.adotado());
		assertEquals(abrigo.getEndereco(), dadosSaidaPet.endereco());
		assertEquals(pet.getImagem(), dadosSaidaPet.imagem());
	}
}
