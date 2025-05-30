package org.serratec.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.serratec.backend.entity.pk.ClientePerfilPK;

import java.time.LocalDateTime;

@Entity
public class ClientePerfil {
    @EmbeddedId
    private ClientePerfilPK id = new ClientePerfilPK();

    private LocalDateTime dataCadastro;

    public ClientePerfil() {
    }

    public ClientePerfil(Cliente cliente, Perfil perfil) {
        id.setCliente(cliente);
        id.setPerfil(perfil);
        this.dataCadastro = LocalDateTime.now();
    }

    public ClientePerfilPK getId() {
        return id;
    }

    public void setId(ClientePerfilPK id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return id.getCliente();
    }

    public void setCliente(Cliente cliente) {
        id.setCliente(cliente);
    }

    public Perfil getPerfil() {
        return id.getPerfil();
    }

    public void setPerfil(Perfil perfil) {
        id.setPerfil(perfil);
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
