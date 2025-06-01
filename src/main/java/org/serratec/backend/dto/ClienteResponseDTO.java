package org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.backend.entity.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        LocalDate cadastro,
        Boolean status,
        List<PedidoResponseDTO> pedidos
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getCadastro(),
                cliente.getStatus(),
                cliente.getPedidos() != null
                        ? cliente.getPedidos().stream().map(PedidoResponseDTO::new).toList()
                        : List.of()
        );
    }
}
