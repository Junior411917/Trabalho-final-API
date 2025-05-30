package org.serratec.backend.dto;

import org.serratec.backend.entity.Perfil;

public record PerfilResponseDTO(Long id, String nome) {
    public PerfilResponseDTO(Perfil perfil){
        this(perfil.getId(),perfil.getNome());
    }
}
