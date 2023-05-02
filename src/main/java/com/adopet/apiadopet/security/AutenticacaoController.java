package com.adopet.apiadopet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adopet.apiadopet.domains.usuario.DadosCredenciaisUsuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity fazerLogin(@RequestBody @Valid DadosCredenciaisUsuario dadosUsuario) {
		try {
			var authenticationToken = new UsernamePasswordAuthenticationToken(
					dadosUsuario.email(),
					dadosUsuario.senha());

			var authentication = authenticationManager.authenticate(authenticationToken);

			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

			return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body("Login ou senha incorreto.");
		}
	}
}
