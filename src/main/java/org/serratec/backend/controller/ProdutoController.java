package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	 @Operation(summary = "Lista todos produtos", description = "A requisição lista todos os produtos")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Lista todos produtos"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	 
	 @GetMapping
	 public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
	        return ResponseEntity.ok().body(produtoService.findAll());
	    }
	 
	 
	 @Operation(summary = "Lista o produto baseado em uma busca por id", description = "Através de uma busca por id a requisição retorna o produto")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Busca o produto pelo ID"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	    @GetMapping("/{id}")
	    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
	        return ResponseEntity.ok().body(produtoService.findById(id));
	    }
	 

	 @Operation(summary = "Insere um novo produto", description = "Através da requisição e os dados passados por ela, é inserido um novo produto")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Insere um novo produto"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	 	@PostMapping
	 	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO produto){
	 		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.inserir(produto));
	 	}
	 	
	 @Operation(summary = "Atualiza um produto existente", description = "Através da requisição e os dados passados por ela, dados de um produto existente são atualizados")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Atualiza um produto existente"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	 	@PutMapping("/{id}")
	 	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produto){
	 		return ResponseEntity.ok().body(produtoService.atualizar(id,produto));
	 	}
	 	
	   @Operation(summary = "Deleta um produto existente", description = "Através de uma busca por id a requisição deleta o produto")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Deleta um produto existente"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	 	@DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
	        produtoService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	
}
