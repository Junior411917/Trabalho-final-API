package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.enums.EstadoDoPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PedidoResponseDTO(Long id, Integer quantidade, Double desconto, LocalDate dataPedido, LocalDateTime horaPedido, LocalDate dataEntrega, EstadoDoPedido status, Cliente cliente) {
    public PedidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getQuantidade(), pedido.getDesconto(), pedido.getDataPedido(), pedido.getHoraPedido(), pedido.getDataEntrega(), pedido.getStatus(), pedido.getCliente());
    }
}
