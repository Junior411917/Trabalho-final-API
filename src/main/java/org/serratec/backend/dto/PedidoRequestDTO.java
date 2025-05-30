package org.serratec.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.enums.EstadoDoPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidoRequestDTO {
    @Size(min = 1, message = "A quantidade minima é 1 (um) item!")
    @NotNull(message = "A quantidade do pedido não pode ser nula!")
    private Integer quantidade;
    private Double desconto;

    @NotNull(message = "A data do pedido não ser nula!")
    private LocalDate dataPedido;

    @NotNull(message = "A hora do pedido não ser nula!")
    private LocalDateTime horaPedido;

    @NotNull(message = "A data do pedido não ser nula!")
    private LocalDate dataEntrega;

    @NotNull(message = "A status do pedido não ser nulo!")
    private EstadoDoPedido status;

    @NotNull(message = "O cliente do pedido não ser nulo!")
    private Cliente cliente;

    public PedidoRequestDTO(Pedido pedido) {
        this.quantidade = pedido.getQuantidade();
        this.desconto = pedido.getDesconto();
        this.dataPedido = pedido.getDataPedido();
        this.horaPedido = pedido.getHoraPedido();
        this.dataEntrega = pedido.getDataEntrega();
        this.status = pedido.getStatus();
        this.cliente = pedido.getCliente();
    }

    public PedidoRequestDTO() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalDateTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public EstadoDoPedido getStatus() {
        return status;
    }

    public void setStatus(EstadoDoPedido status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
