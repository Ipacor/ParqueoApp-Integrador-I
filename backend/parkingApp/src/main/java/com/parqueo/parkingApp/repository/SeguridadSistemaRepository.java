package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.SeguridadSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeguridadSistemaRepository extends JpaRepository<SeguridadSistema, Long> {

    Optional<SeguridadSistema> findByUsuarioId(Long usuarioId);
}
