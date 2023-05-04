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

import com.adopet.apiadopet.domains.pet.DadosAtualizacaoPet;
import com.adopet.apiadopet.domains.pet.DadosEntradaPet;
import com.adopet.apiadopet.domains.pet.DadosListagemPet;
import com.adopet.apiadopet.domains.pet.DadosSaidaPet;
import com.adopet.apiadopet.services.PetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService petService;

	@Transactional
	@PostMapping
	public ResponseEntity<DadosSaidaPet> cadastrarPet(
			@Valid @RequestBody DadosEntradaPet dadosEntradaPet,
			UriComponentsBuilder uComponentsBuilder) {

		var pet = petService.cadastrarPet(dadosEntradaPet);
		var uri = uComponentsBuilder.path("/pets/{id}")
				.buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosSaidaPet(pet));

	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemPet>> listarPets(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable pageable) {
		Page<DadosListagemPet> listaPets = petService.listarPets(pageable);

		return ResponseEntity.ok().body(listaPets);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosSaidaPet> listarPetPorId(@PathVariable Long id) {
		var pet = petService.listarPetPorId(id);

		return ResponseEntity.ok().body(pet);

	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deletarPetPorId(@PathVariable Long id) {
		petService.deletarPetPorId(id);

		Map<String, String> response = new HashMap<>();
		response.put("memsagem", "Pet deletado com sucesso");

		return ResponseEntity.ok().body(response);

	}

	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<DadosSaidaPet> atualizarPetPorId(@PathVariable Long id,
			@Valid @RequestBody DadosAtualizacaoPet dadosAtualizacaoPet) {
		var petAtualizado = petService.atualizarPetPorId(id, dadosAtualizacaoPet);
		return ResponseEntity.ok().body(petAtualizado);

	}
}
