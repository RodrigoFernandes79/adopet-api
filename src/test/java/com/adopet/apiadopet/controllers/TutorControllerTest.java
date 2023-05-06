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

import com.adopet.apiadopet.domains.tutor.DadosEntradaTutor;
import com.adopet.apiadopet.domains.tutor.Tutor;
import com.adopet.apiadopet.services.TutorService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class TutorControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TutorService tutorService;

	@Autowired
	private JacksonTester<DadosEntradaTutor> dadosEntradaJTester;

	@Test
	@DisplayName("Deveria retornar um codigo 201 quando informações estiverem válidas")
	void testCadastrarTutorCenario_01() throws IOException, Exception {

		var tutor = new Tutor(null, "nome", null, null, "nome@email.com", "R4f43llll!", null, null, null);

		when(tutorService.cadastrarTutor(any())).thenReturn(tutor);

		var response = mockMvc.perform(post("/tutores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosEntradaJTester.write(new DadosEntradaTutor("nome", "nome@email.com", "R4f43llll!", "R4f43llll!"))
						.getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

	}

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
	void testCadastrarTutorCenario_02() throws IOException, Exception {
		var tutor = new Tutor(null, "nome", null, null, "nome@email.com", "R4f43llll!", null, null, null);

		when(tutorService.cadastrarTutor(any())).thenReturn(tutor);

		var response = mockMvc.perform(post("/tutores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosEntradaJTester.write(new DadosEntradaTutor(null, null, null, "R4f43llll!"))
						.getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}

}
