package org.serratec.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.exception.ProdutoException;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
    
    @Transactional
	public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setPrecoProduto(produtoDTO.getPrecoProduto());
		produto.setCategoria(produtoDTO.getCategoria());
		
		repository.save(produto);
		return new ProdutoResponseDTO(produto);
	}
	
    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO){
        Optional<Produto> produto = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ClienteException("ID não encontrado.")));

        produto.ifPresent(p -> {
            p.setId(id);
            p.setNomeProduto(produtoRequestDTO.getNomeProduto());
            p.setDescricaoProduto(produtoRequestDTO.getDescricaoProduto());
            p.setPrecoProduto(produtoRequestDTO.getPrecoProduto());
            p.setCategoria(produtoRequestDTO.getCategoria());
        });

        repository.save(produto.get());
  
        return new ProdutoResponseDTO(produto.get());
    }
    
    public void delete(Long id){
        Optional<Produto> produto = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ProdutoException("ID não encontrado.")));

        produto.ifPresent(value -> repository.deleteById(value.getId()));
    }
}
