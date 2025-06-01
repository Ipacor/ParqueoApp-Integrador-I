package com.parqueo.parkingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_uso")
public class HistorialUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "espacio_id", nullable = false)
    private EspacioDisponible espacio;

    private LocalDateTime fechaUso;

    private String accion;

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

    public EspacioDisponible getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDisponible espacio) {
        this.espacio = espacio;
    }

    public LocalDateTime getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(LocalDateTime fechaUso) {
        this.fechaUso = fechaUso;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public HistorialUso() {
    }

    public HistorialUso(Long id, Usuario usuario, EspacioDisponible espacio, LocalDateTime fechaUso, String accion) {
        this.id = id;
        this.usuario = usuario;
        this.espacio = espacio;
        this.fechaUso = fechaUso;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "HistorialUso [id=" + id + ", usuario=" + usuario + ", espacio=" + espacio + ", fechaUso=" + fechaUso
                + ", accion=" + accion + "]";
    }
    
}
