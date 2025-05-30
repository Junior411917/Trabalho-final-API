package org.serratec.backend.entity;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.enums.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeProduto;
	private String descricaoProduto;
	private Double precoProduto;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@OneToMany(mappedBy = "id.produto")
	private List<PedidoProduto> pedidosProdutos = new ArrayList<>();

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nomeProduto=" + nomeProduto + ", descricaoProduto=" + descricaoProduto
				+ ", precoProduto=" + precoProduto + ", categoria=" + categoria + ", pedidosProdutos=" + pedidosProdutos
				+ "]";
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public List<PedidoProduto> getPedidosProdutos() {
		return pedidosProdutos;
	}

	public void setPedidosProdutos(List<PedidoProduto> pedidosProdutos) {
		this.pedidosProdutos = pedidosProdutos;
	}
}
