package com.parqueo.parkingApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.parqueo.parkingApp.model.Usuario;
import com.parqueo.parkingApp.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")  
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService service) {
        this.usuarioService = service;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   @PostMapping
public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
    try {
        Usuario creado = usuarioService.save(usuario);
        return ResponseEntity.ok(creado);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@PostMapping("/login")
public ResponseEntity<Usuario> login(@RequestBody Usuario loginRequest) {
    Optional<Usuario> usuarioOpt = usuarioService.findByUsername(loginRequest.getUsername());
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        if (usuario.getPassword().equals(loginRequest.getPassword())) {
            // contraseña correcta
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}



    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.getById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setUsername(usuario.getUsername());
                    usuarioExistente.setPassword(usuario.getPassword());
                    usuarioExistente.setNombreCompleto(usuario.getNombreCompleto());
                    usuarioExistente.setEmail(usuario.getEmail());
                    usuarioExistente.setRol(usuario.getRol());
                    return ResponseEntity.ok(usuarioService.save(usuarioExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}