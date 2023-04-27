package com.adopet.apiadopet.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adopet.apiadopet.domains.pet.DadosEntradaPet;
import com.adopet.apiadopet.domains.pet.DadosListagemPet;
import com.adopet.apiadopet.domains.pet.DadosSaidaPet;
import com.adopet.apiadopet.services.PetService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pets")
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
	public ResponseEntity<List<DadosListagemPet>> listarPets() {
		List<DadosListagemPet> listaPets = petService.listarPets();

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
}