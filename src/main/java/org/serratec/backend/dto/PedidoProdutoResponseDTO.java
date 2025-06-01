package org.serratec.backend.dto;

import org.serratec.backend.entity.PedidoProduto;

public record PedidoProdutoResponseDTO(Long idProduto, String nomeProduto, Double precoProduto, Integer quantidade, Double desconto, Double venda) {
    public PedidoProdutoResponseDTO(PedidoProduto pedidoProduto) {
        this(
                pedidoProduto.getId().getProduto().getId(),
                pedidoProduto.getId().getProduto().getNome(),
                pedidoProduto.getId().getProduto().getPreco(),
                pedidoProduto.getQuantidade(),
                pedidoProduto.getDesconto(),
                pedidoProduto.getVenda()
        );
    }
}
