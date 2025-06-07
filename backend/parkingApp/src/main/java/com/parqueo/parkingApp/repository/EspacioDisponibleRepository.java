package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.EspacioDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioDisponibleRepository extends JpaRepository<EspacioDisponible, Long> {
}
