package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;

public record ClienteResponseDTO (String nome, String telefone, String email, String cpf) {
    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
    }
}
