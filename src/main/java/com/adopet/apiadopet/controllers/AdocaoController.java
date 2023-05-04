package com.adopet.apiadopet.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adopet.apiadopet.domains.adocao.DadosEntradaAdocao;
import com.adopet.apiadopet.domains.adocao.DadosSaidaAdocao;
import com.adopet.apiadopet.services.AdocaoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/adocao")
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

	@Transactional
	@DeleteMapping("/{id_pet}")
	public ResponseEntity<Map<String, String>> deletarAdocaoPorPetId(
			@PathVariable Long id_pet) {

		adocaoService.deletarAdocaoPorPetId(id_pet);
		Map<String, String> response = new HashMap<>();
		response.put("mensagem", "Adoção deletada com sucesso!");

		return ResponseEntity.ok().body(response);

	}

}
