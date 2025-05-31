package org.serratec.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.PedidoProduto;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.PedidoException;
import org.serratec.backend.repository.PedidoProdutoRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;

	public List<PedidoResponseDTO> findAll() {
		return pedidoRepository.findAll().stream().map(PedidoResponseDTO::new).collect(Collectors.toList());
	}

	public PedidoResponseDTO findById(Long id) {
		return pedidoRepository.findById(id).map(PedidoResponseDTO::new)
				.orElseThrow(() -> new PedidoException("Id não encontrado"));
	}

	@Transactional
	public PedidoResponseDTO save(PedidoRequestDTO pedidoRequestDTO) {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(pedidoRequestDTO.getDataPedido());
		pedido.setHoraPedido(pedidoRequestDTO.getHoraPedido());
		pedido.setDataEntrega(pedidoRequestDTO.getDataEntrega());
		pedido.setStatus(pedidoRequestDTO.getStatus());
		pedido.setCliente(pedidoRequestDTO.getCliente());

		for (PedidoProduto pp : pedidoRequestDTO.getPedidosProdutos()) {
			Produto produto = produtoRepository.findById(pp.getId().getProduto().getId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + pp.getId().getProduto().getId()));

			pp.setPedido(pedido);
			pp.setProduto(produto);
			pp.setQuantidade(pp.getQuantidade());
			pp.setDesconto(pp.getDesconto());
			pp.setVenda(pp.getId().getProduto().getPrecoProduto() * pp.getQuantidade() - pp.getDesconto());
		}
		pedidoRepository.save(pedido);
		pedidoProdutoRepository.saveAll(pedidoRequestDTO.getPedidosProdutos());
		return new PedidoResponseDTO(pedido);
	}

	@Transactional
	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedidoRequestDTO) {
		Optional<Pedido> pedido = Optional.ofNullable(
				pedidoRepository.findById(id).orElseThrow(() -> new PedidoException("produto não encontrado!")));

		pedido.ifPresent(p -> {
			p.setId(id);
			p.setDataPedido(pedidoRequestDTO.getDataPedido());
			p.setHoraPedido(pedidoRequestDTO.getHoraPedido());
			p.setDataEntrega(pedidoRequestDTO.getDataEntrega());
			p.setStatus(pedidoRequestDTO.getStatus());
			p.setCliente(pedidoRequestDTO.getCliente());
		});
		pedidoRepository.save(pedido.get());
		return new PedidoResponseDTO(pedido.get());

	}

	public void deletar(Long id) {
		Optional<Pedido> pedido = Optional
				.ofNullable(pedidoRepository.findById(id).orElseThrow(() -> new PedidoException("Id não encontrado!")));
		pedido.ifPresent(value -> pedidoRepository.deleteById(value.getId()));
	}
}
