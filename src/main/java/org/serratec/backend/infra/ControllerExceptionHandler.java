package org.serratec.backend.infra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.exception.PerfilException;
import org.serratec.backend.exception.ProdutoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();

		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
			erros.add(erro.getField() + ":" + erro.getDefaultMessage());
		}

		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(), erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		ErroResposta erroResposta = new ErroResposta(status.value(), "Há campos inválidos", LocalDateTime.now(), erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	@ExceptionHandler(PerfilException.class)
	public ResponseEntity<ErroResposta> handlePerfilException(PerfilException ex) {
		ErroResposta erroResposta = new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Foi inserido um valor invalido na requisição!", LocalDateTime.now(), ex.getMessage().lines().toList());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResposta);
	}

	@ExceptionHandler(ClienteException.class)
	public ResponseEntity<ErroResposta> handleClienteException(ClienteException ex) {
		ErroResposta erroResposta = new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Foi inserido um valor invalido na requisição!", LocalDateTime.now(), ex.getMessage().lines().toList());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResposta);
	}
	
	@ExceptionHandler(ProdutoException.class)
    public ResponseEntity<ErroResposta> handleProdutoException(ProdutoException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Foi inserido um valor invalido na requisição!", LocalDateTime.now(), ex.getMessage().lines().toList());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResposta);
    }
}
