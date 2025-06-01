package com.parqueo.parkingApp.service;

import org.springframework.stereotype.Service;

import com.parqueo.parkingApp.model.Usuario;
import com.parqueo.parkingApp.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repo) {
        this.usuarioRepository = repo;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        boolean esNuevo = (usuario.getId() == null);

        if ("ADMIN".equalsIgnoreCase(usuario.getRol())) {
            Optional<Usuario> adminExistente = usuarioRepository.findByUsername(usuario.getUsername());

            if (esNuevo && usuarioRepository.existsByRol("ADMIN")) {
                throw new RuntimeException("Ya existe un administrador en el sistema.");
            } else if (!esNuevo && usuarioRepository.existsByRol("ADMIN")) {
                // Validar si está cambiando de otro rol a ADMIN
                Usuario usuarioOriginal = usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
                if (!"ADMIN".equalsIgnoreCase(usuarioOriginal.getRol())) {
                    throw new RuntimeException("Ya existe un administrador en el sistema.");
                }
            }
        }

        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
