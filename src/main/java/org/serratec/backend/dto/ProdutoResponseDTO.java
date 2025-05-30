package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;
import org.serratec.backend.enums.Categoria;

public record ProdutoResponseDTO(Long id, String nomeProduto, String descricaoProduto, Double precoProduto, Categoria categoria) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNomeProduto(), produto.getDescricaoProduto(), produto.getPrecoProduto(), produto.getCategoria());
    }
}
