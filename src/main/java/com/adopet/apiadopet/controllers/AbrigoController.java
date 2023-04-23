package com.adopet.apiadopet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosSaidaAbrigo;
import com.adopet.apiadopet.services.AbrigoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("abrigos")
public class AbrigoController {

	@Autowired
	private AbrigoService abrigoService;

	@Transactional
	@PostMapping
	public ResponseEntity<DadosSaidaAbrigo> cadastrarAbrigo(
			@Valid @RequestBody DadosEntradaAbrigo dadosEntradaAbrigo,
			UriComponentsBuilder uriComponentsBuilder) {
		var abrigo = abrigoService.cadastrarAbrigo(dadosEntradaAbrigo);
		var uri = uriComponentsBuilder.path("/abrigos{id}")
				.buildAndExpand(abrigo.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosSaidaAbrigo(abrigo));

	}

}
