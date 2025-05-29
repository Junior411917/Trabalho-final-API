package org.serratec.backend.entity;

import org.serratec.backend.entity.pk.PedidoProdutoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class PedidoProduto {

	@EmbeddedId
	private PedidoProdutoPK id = new PedidoProdutoPK();
	
	
	private Double desconto;
	
	private Integer quantidade;
	
	private Double venda;
	
	

	public PedidoProduto(Pedido pedido, Produto produto) {
		
		id.setPedido(pedido); 
		id.setProduto(produto);
		this.desconto = pedido.getDesconto();
		this.quantidade = pedido.getQuantidade();
		this.venda = produto.getPrecoProduto() * quantidade - desconto; 
		
	}

	
	
	public PedidoProdutoPK getId() {
		return id;
	}

	public void setId(PedidoProdutoPK id) {
		this.id = id;
	}

	public Double getVenda() {
		return venda;
	}

	public void setVenda(Double venda) {
		this.venda = venda;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
