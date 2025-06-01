package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;
import org.serratec.backend.enums.Categoria;

import java.time.LocalDate;

public record ProdutoResponseDTO(Long id, String nome, String descricao, Double preco, Integer estoque, LocalDate validade, Categoria categoria) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getEstoque(), produto.getValidade(), produto.getCategoria());
    }
}
