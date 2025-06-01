package com.parqueo.parkingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguridad_sistema")
public class SeguridadSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    private String tipoAutenticacion;

    @Column(length = 1000)
    private String registroAccesos;

    private LocalDateTime fechaRegistro;

    private String estado;

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

    public String getTipoAutenticacion() {
        return tipoAutenticacion;
    }

    public void setTipoAutenticacion(String tipoAutenticacion) {
        this.tipoAutenticacion = tipoAutenticacion;
    }

    public String getRegistroAccesos() {
        return registroAccesos;
    }

    public void setRegistroAccesos(String registroAccesos) {
        this.registroAccesos = registroAccesos;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SeguridadSistema() {
    }

    public SeguridadSistema(Long id, Usuario usuario, String tipoAutenticacion, String registroAccesos,
            LocalDateTime fechaRegistro, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.tipoAutenticacion = tipoAutenticacion;
        this.registroAccesos = registroAccesos;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SeguridadSistema [id=" + id + ", usuario=" + usuario + ", tipoAutenticacion=" + tipoAutenticacion
                + ", registroAccesos=" + registroAccesos + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado
                + "]";

    }
}
