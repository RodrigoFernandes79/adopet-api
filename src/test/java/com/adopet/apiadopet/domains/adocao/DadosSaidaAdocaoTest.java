package com.adopet.apiadopet.domains.adocao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.adopet.apiadopet.domains.pet.Pet;
import com.adopet.apiadopet.domains.tutor.Tutor;

public class DadosSaidaAdocaoTest {

	@Test
	void DeveriaConverterAdocaoEmDadosSaidaAdocao() {
		// Cria uma instância de Adocao
		UUID adocaoId = UUID.randomUUID();
		Pet pet = new Pet();
		pet.setId(1L);
		Tutor tutor = new Tutor();
		tutor.setId(2L);
		LocalDateTime data = LocalDateTime.of(2022, 5, 5, 12, 0, 0);
		Adocao adocao = new Adocao(adocaoId, pet, tutor, data);

		// Cria uma instância de DadosSaidaAdocao a partir de Adocao
		DadosSaidaAdocao dadosSaidaAdocao = new DadosSaidaAdocao(adocao);

		// Verifica se os valores dos atributos são os mesmos
		assertEquals(adocaoId, dadosSaidaAdocao.id());
		assertEquals(pet.getId(), dadosSaidaAdocao.animal());
		assertEquals(tutor.getId(), dadosSaidaAdocao.tutor());
		assertEquals(data, dadosSaidaAdocao.data());
	}
}
