package org.serratec.backend.dto;

import org.serratec.backend.entity.Pedido;
import org.serratec.backend.enums.EstadoDoPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDate dataPedido,
        LocalDateTime horaPedido,
        LocalDate dataEntrega,
        EstadoDoPedido status,
        ClienteMinimalDTO cliente,
        List<PedidoProdutoResponseDTO> pedidosProdutos
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getHoraPedido(),
                pedido.getDataEntrega(),
                pedido.getStatus(),
                new ClienteMinimalDTO(pedido.getCliente()),
                pedido.getPedidosProdutos() != null
                        ? pedido.getPedidosProdutos().stream().map(PedidoProdutoResponseDTO::new).toList()
                        : List.of()
        );
    }
}
