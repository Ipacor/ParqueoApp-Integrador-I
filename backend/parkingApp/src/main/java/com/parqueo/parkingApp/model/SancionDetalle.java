package com.parqueo.parkingApp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SancionDetalle")
public class SancionDetalle {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String descripcion;
    private LocalDateTime fechaSancion;

    @Enumerated(EnumType.STRING)
    private NivelGravedad nivelGravedad;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_sancion", nullable = false)
    private Sancion sancion;

    @ManyToOne
    @JoinColumn(name = "id_regla", nullable = false)
    private ReglasEstacionamiento regla;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaSancion() {
        return fechaSancion;
    }

    public void setFechaSancion(LocalDateTime fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    public NivelGravedad getNivelGravedad() {
        return nivelGravedad;
    }

    public void setNivelGravedad(NivelGravedad nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Sancion getSancion() {
        return sancion;
    }

    public void setSancion(Sancion sancion) {
        this.sancion = sancion;
    }

    public ReglasEstacionamiento getRegla() {
        return regla;
    }

    public void setRegla(ReglasEstacionamiento regla) {
        this.regla = regla;
    }

    public SancionDetalle() {
    }

    public SancionDetalle(Long id, String descripcion, LocalDateTime fechaSancion, NivelGravedad nivelGravedad,
            String estado, Sancion sancion, ReglasEstacionamiento regla) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaSancion = fechaSancion;
        this.nivelGravedad = nivelGravedad;
        this.estado = estado;
        this.sancion = sancion;
        this.regla = regla;
    }

    @Override
    public String toString() {
        return "SancionDetalle [id=" + id + ", descripcion=" + descripcion + ", fechaSancion=" + fechaSancion
                + ", nivelGravedad=" + nivelGravedad + ", estado=" + estado + ", sancion=" + sancion + ", regla="
                + regla + "]";
    }

    public enum NivelGravedad {
        LEVE("Llamada de atención"),
        INTERMEDIA("Suspensión temporal"),
        GRAVE("Suspensión definitiva");

        private final String descripcion;

        NivelGravedad(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

}
