package com.adopet.apiadopet.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.adopet.apiadopet.domains.abrigo.Abrigo;
import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.endereco.DadosEntradaEndereco;
import com.adopet.apiadopet.domains.endereco.Endereco;
import com.adopet.apiadopet.services.AbrigoService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class AbrigoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AbrigoService abrigoService;

	@Autowired
	private JacksonTester<DadosEntradaAbrigo> dadosEntradaJTester;

	@Test
	@DisplayName("Deveria retornar um codigo 201 quando informações estiverem válidas")
	void testCadastrarAbrigoCenario_01() throws IOException, Exception {

		var abrigo = new Abrigo(null, "Nome",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeyZAyJNYzHM71aZGvTEA36SOpXro_zVGGng&usqp=CAU",
				"00.394.460/0001-41", "998852474", "fulano@email.com", "R4f43l!!!", "ADOTE UM CAO",
				new Endereco("QUIXABÁ", "PE"), null);

		when(abrigoService.cadastrarAbrigo(any())).thenReturn(abrigo);

		var response = mockMvc.perform(post("/abrigos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosEntradaJTester.write(new DadosEntradaAbrigo("Nome",
						"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeyZAyJNYzHM71aZGvTEA36SOpXro_zVGGng&usqp=CAU",
						"00.394.460/0001-41", "998852474", "fulano@email.com", "R4f43l!!!", "R4f43l!!!",
						new DadosEntradaEndereco("QUIXABÁ", "PE"), "ADOTE UM CAO")).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

	}

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
	void testCadastrarAbrigoCenario_02() throws IOException, Exception {
		var abrigo = new Abrigo(null, "Nome",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeyZAyJNYzHM71aZGvTEA36SOpXro_zVGGng&usqp=CAU",
				"00.394.460/0001-41", "998852474", "fulano@email.com", "R4f43l!!!", "ADOTE UM CAO",
				new Endereco("QUIXABÁ", "PE"), null);

		when(abrigoService.cadastrarAbrigo(any())).thenReturn(abrigo);

		var response = mockMvc.perform(post("/abrigos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosEntradaJTester.write(new DadosEntradaAbrigo(null,
						null, null, null, null, null, "R4f43l!!!",
						new DadosEntradaEndereco("QUIXABÁ", "PE"), "ADOTE UM CAO")).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}

}
