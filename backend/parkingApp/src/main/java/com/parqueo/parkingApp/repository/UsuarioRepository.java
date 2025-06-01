package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

        // Método para verificar si ya existe un usuario con un rol dado
    boolean existsByRol(String rol);
    
    //  buscar por username, para login o validaciones
    boolean existsByUsername(String username);
}