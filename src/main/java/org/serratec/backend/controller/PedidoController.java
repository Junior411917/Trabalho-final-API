package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;

	@Operation(summary = "Lista todos pedidos", description = "A requisição lista todos os pedidos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Pedido.class), mediaType = "application/json") }, description = "Lista todos pedidos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> findAll() {
		return ResponseEntity.ok().body(pedidoService.findAll());
	}

	@Operation(summary = "Lista o pedido baseado em uma busca por id", description = "Através de uma busca por id a requisição retorna o pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Pedido.class), mediaType = "application/json") }, description = "Busca o pedido pelo ID"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(pedidoService.findById(id));
	}

	@Operation(summary = "Insere um novo pedido", description = "Através da requisição e os dados passados por ela, é inserido um novo pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Pedido.class), mediaType = "application/json") }, description = "Insere um novo pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PostMapping
	public ResponseEntity<PedidoResponseDTO> save(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoRequestDTO));
	}

	@Operation(summary = "Atualiza um pedido existente", description = "Através da requisição e os dados passados por ela, dados de um pedido existente são atualizados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Pedido.class), mediaType = "application/json") }, description = "Atualiza um pedido existente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PutMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
		return ResponseEntity.ok().body(pedidoService.atualizar(id, pedidoRequestDTO));
	}

	@Operation(summary = "Deleta um pedido existente", description = "Através de uma busca por id a requisição deleta o pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Pedido.class), mediaType = "application/json") }, description = "Deleta um pedido existente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@DeleteMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> deletar(@PathVariable Long id) {
		pedidoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
