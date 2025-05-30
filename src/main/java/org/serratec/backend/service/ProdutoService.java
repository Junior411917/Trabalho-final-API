package org.serratec.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.ProdutoException;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoResponseDTO> findAll(){
        return repository.findAll().stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    public ProdutoResponseDTO findById(Long id){
        return repository.findById(id).map(ProdutoResponseDTO::new).orElseThrow(() -> new ProdutoException("ID não encontrado."));
    }
    
	public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setPrecoProduto(produtoDTO.getPrecoProduto());
		produto.setCategoria(produtoDTO.getCategoria());
		
		System.out.println(produto);
		repository.save(produto);
		return new ProdutoResponseDTO(produto);
	}
}
