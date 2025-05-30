package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Perfil;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	 @Operation(summary = "Lista todos os produtos para pedidos", description = "A requisição lista todos os produtos ativos que podem ser vinculados a um ou mais pedidos")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Produto.class), mediaType = "application/json") }, description = "Lista todos os produtos para pedidos"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	 
	 @GetMapping
	 public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
	        return ResponseEntity.ok().body(produtoService.findAll());
	    }
	 
	 
	 @Operation(summary = "Lista o Produto buscado pelo ID", description = "A requisição lista o perfil buscando pelo ID ativo que pode ser vinculado a um ou mais usuários")
	    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Perfil.class), mediaType = "application/json") }, description = "Lista o perfil buscado pelo ID"),
	            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
	            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
	            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
	    )
	    @GetMapping("/{id}")
	    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
	        return ResponseEntity.ok().body(produtoService.findById(id));
	    }
	 
	 	@PostMapping
	 	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO produto){
	 		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.inserir(produto));
	 	}
	 	
	 	//@PutMapping
	 	
	 	
	
}
