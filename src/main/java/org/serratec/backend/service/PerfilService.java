package org.serratec.backend.service;

import org.serratec.backend.dto.PerfilResponseDTO;
import org.serratec.backend.exception.PerfilException;
import org.serratec.backend.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository repository;

    public List<PerfilResponseDTO> findAll(){
        return repository.findAll().stream().map(PerfilResponseDTO::new).collect(Collectors.toList());
    }

    public PerfilResponseDTO findById(Long id){
        return repository.findById(id).map(PerfilResponseDTO::new).orElseThrow(() -> new PerfilException("ID não encontrado."));
    }
}
