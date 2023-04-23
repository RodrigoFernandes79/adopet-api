package com.adopet.apiadopet.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adopet.apiadopet.domains.DadosAtualizacaoTutor;
import com.adopet.apiadopet.domains.DadosEntradaTutor;
import com.adopet.apiadopet.domains.DadosListagemTutor;
import com.adopet.apiadopet.domains.DadosSaidaTutor;
import com.adopet.apiadopet.services.TutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tutores")
public class TutorController {

	@Autowired
	private TutorService tutorService;

	@Transactional
	@PostMapping
	public ResponseEntity<DadosSaidaTutor> cadastrarTutor(
			@Valid @RequestBody DadosEntradaTutor dadosEntradaTutor,
			UriComponentsBuilder uriComponentsBuilder) {

		var tutor = tutorService.cadastrarTutor(dadosEntradaTutor);
		var Uri = uriComponentsBuilder
				.path("/tutores/{id}").buildAndExpand(tutor.getId()).toUri();
		return ResponseEntity.created(Uri).body(new DadosSaidaTutor(tutor));

	}

	@GetMapping
	public ResponseEntity<List<DadosListagemTutor>> retornarTutores() {
		List<DadosListagemTutor> tutores = tutorService.retornarTutores();

		return ResponseEntity.ok().body(tutores);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosSaidaTutor> retornarTutorPorId(@PathVariable Long id) {
		var tutor = tutorService.retornarTutorPorId(id);

		return ResponseEntity.ok().body(tutor);

	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deletarTutorPorid(@PathVariable Long id) {
		tutorService.deletarTutorPorid(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Tutor deletado com sucesso.");

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<DadosSaidaTutor> alterarTutorPorId(@PathVariable Long id,
			@Valid @RequestBody DadosAtualizacaoTutor dadosAtualizacaoTutor) {

		DadosSaidaTutor tutorAlterado = tutorService.alterarTutorPorId(id, dadosAtualizacaoTutor);
		return ResponseEntity.ok().body(tutorAlterado);

	}
}
