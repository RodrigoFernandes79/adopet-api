package com.adopet.apiadopet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adopet.apiadopet.domains.DadosEntradaTutor;
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
	public ResponseEntity<List<DadosSaidaTutor>> retornarTutores() {
		List<DadosSaidaTutor> tutores = tutorService.retornarTutores();

		return ResponseEntity.ok().body(tutores);

	}

}
