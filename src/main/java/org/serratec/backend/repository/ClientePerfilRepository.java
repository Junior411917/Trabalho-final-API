package org.serratec.backend.repository;

import org.serratec.backend.entity.ClientePerfil;
import org.serratec.backend.entity.pk.ClientePerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientePerfilRepository extends JpaRepository<ClientePerfil, ClientePerfilPK> {
}
