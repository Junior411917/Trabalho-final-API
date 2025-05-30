package org.serratec.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.serratec.backend.dto.PerfilResponseDTO;
import org.serratec.backend.entity.Perfil;
import org.serratec.backend.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    @Autowired
    private PerfilService service;

    @Operation(summary = "Lista todos os perfis para usuários", description = "A requisição lista todos os perfis ativos que podem ser vinculados a um ou mais usuários")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Perfil.class), mediaType = "application/json") }, description = "Lista todos os perfis para usuários"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(summary = "Lista o perfil buscado pelo ID", description = "A requisição lista o perfil buscando pelo ID ativo que pode ser vinculado a um ou mais usuários")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Perfil.class), mediaType = "application/json") }, description = "Lista o perfil buscado pelo ID"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
