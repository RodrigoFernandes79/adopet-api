package com.adopet.apiadopet.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adopet.apiadopet.domains.abrigo.DadosAtualizacaoAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosEntradaAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosListagemAbrigo;
import com.adopet.apiadopet.domains.abrigo.DadosSaidaAbrigo;
import com.adopet.apiadopet.services.AbrigoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/abrigos")
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

	@GetMapping
	public ResponseEntity<Page<DadosListagemAbrigo>> listarAbrigo(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable pageable) {
		var abrigo = abrigoService.listarAbrigo(pageable);

		return ResponseEntity.ok().body(abrigo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosSaidaAbrigo> listarAbrigoPorId(@PathVariable Long id) {
		var abrigo = abrigoService.listarAbrigoPorId(id);

		return ResponseEntity.ok().body(abrigo);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deletarAbrigoPorId(@PathVariable Long id) {
		abrigoService.deletarAbrigoPorId(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Abrigo deletado com Sucesso!");
		return ResponseEntity.ok().body(response);

	}

	@Transactional
	@PatchMapping("{id}")
	public ResponseEntity<DadosSaidaAbrigo> atualizarAbrigoPorId(
			@Valid @RequestBody DadosAtualizacaoAbrigo dadosAtualizacaoAbrigo,
			@PathVariable Long id) {

		var abrigoAtualizado = abrigoService.atualizarAbrigoPorId(
				dadosAtualizacaoAbrigo, id);
		return ResponseEntity.ok().body(abrigoAtualizado);

	}

}
