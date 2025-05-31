package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        List<PedidoResponseDTO> pedidos
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getPedidos() != null
                        ? cliente.getPedidos().stream().map(PedidoResponseDTO::new).toList()
                        : List.of()
        );
    }
}
