package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> findAll() {
		return ResponseEntity.ok().body(pedidoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(pedidoService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<PedidoResponseDTO> save(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoRequestDTO));
	}
	

}
