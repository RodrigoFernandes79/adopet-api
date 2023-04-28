package com.adopet.apiadopet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adopet.apiadopet.domains.adocao.DadosEntradaAdocao;
import com.adopet.apiadopet.domains.adocao.DadosSaidaAdocao;
import com.adopet.apiadopet.services.AdocaoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("adocao")
public class AdocaoController {

	@Autowired
	private AdocaoService adocaoService;

	@Transactional
	@PostMapping
	public ResponseEntity<DadosSaidaAdocao> adotarPet(
			@Valid @RequestBody DadosEntradaAdocao dadosEntradaAdocao) {

		var adocao = adocaoService.adotarPet(dadosEntradaAdocao);
		return ResponseEntity.ok().body(adocao);

	}

}
