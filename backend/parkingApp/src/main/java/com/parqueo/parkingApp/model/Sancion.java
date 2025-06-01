package com.parqueo.parkingApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sanciones")
public class Sancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String motivo;

    private String estado;

    // Getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Sancion() {
    }

    public Sancion(Long id, Usuario usuario, String motivo, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.motivo = motivo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sancion [id=" + id + ", usuario=" + usuario + ", motivo=" + motivo + ", estado=" + estado + "]";
    }
}