package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;

public record ClienteMinimalDTO(Long id, String nome) {
    public ClienteMinimalDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome());
    }
}
