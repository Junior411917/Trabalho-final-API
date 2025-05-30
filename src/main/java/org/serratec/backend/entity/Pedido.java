package org.serratec.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.serratec.backend.enums.EstadoDoPedido;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	private Double desconto;
	private LocalDate dataPedido;
	private LocalDateTime horaPedido;
	private LocalDate dataEntrega;

	@Enumerated(EnumType.STRING)
	private EstadoDoPedido status;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@JsonManagedReference
	@OneToMany(mappedBy = "id.pedido")
	private List<PedidoProduto> pedidosProdutos = new ArrayList<>();

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setId(Long id) {
		this.id = id;
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
}
