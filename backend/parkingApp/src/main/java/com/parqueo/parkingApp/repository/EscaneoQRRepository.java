package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.EscaneoQR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscaneoQRRepository extends JpaRepository<EscaneoQR, Long> {

    List<EscaneoQR> findByReservaId(Long reservaId);
}
