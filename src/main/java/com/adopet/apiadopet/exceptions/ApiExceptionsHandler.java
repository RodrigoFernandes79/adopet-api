package com.adopet.apiadopet.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionsHandler {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<String> tratarErroNaoEncontrado(ObjetoNaoEncontrado e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

	}

	@ExceptionHandler(DadosExistenteException.class)
	public ResponseEntity<String> tratarErroDadosExistente(DadosExistenteException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosCampoValidacao>> tratarErroValidacao(MethodArgumentNotValidException e) {
		var errors = e.getFieldErrors();
		var erro = errors.stream().map(DadosCampoValidacao::new).toList();

		return ResponseEntity.badRequest().body(erro);
	}

	private record DadosCampoValidacao(String erro, String mensagem) {
		public DadosCampoValidacao(FieldError erro) {

			this(erro.getField(), erro.getDefaultMessage());
		}
	}

}
