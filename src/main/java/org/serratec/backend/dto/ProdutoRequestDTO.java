package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;
import org.serratec.backend.enums.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {
	@NotBlank(message = "O nome do produto não pode ser nulo ou vazio!")
    private String nomeProduto;
	@NotBlank(message = "A descrição do produto não pode ser nulo ou vazio!")
    private String descricaoProduto;
	@NotNull(message = "O preço não pode ser nulo!")
    private Double precoProduto;
	@NotNull(message = "A categoria não pode ser nula!")
    private Categoria categoria;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(Produto produto) {
        this.nomeProduto = produto.getNomeProduto();
        this.descricaoProduto = produto.getDescricaoProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.categoria = produto.getCategoria();
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
