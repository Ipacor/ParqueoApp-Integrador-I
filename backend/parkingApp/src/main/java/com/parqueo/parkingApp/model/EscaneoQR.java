package com.parqueo.parkingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "escaneos_qr")
public class EscaneoQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // Uno a uno con Reserva
    @OneToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;


    private LocalDateTime timestampEnt;

    private LocalDateTime timestampSal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDateTime getTimestampEnt() {
        return timestampEnt;
    }

    public void setTimestampEnt(LocalDateTime timestampEnt) {
        this.timestampEnt = timestampEnt;
    }

    public LocalDateTime getTimestampSal() {
        return timestampSal;
    }

    public void setTimestampSal(LocalDateTime timestampSal) {
        this.timestampSal = timestampSal;
    }

    public EscaneoQR() {
    }

    public EscaneoQR(Long id, Reserva reserva, LocalDateTime timestampEnt, LocalDateTime timestampSal) {
        this.id = id;
        this.reserva = reserva;
        this.timestampEnt = timestampEnt;
        this.timestampSal = timestampSal;
    }

    @Override
    public String toString() {
        return "EscaneoQR [id=" + id + ", reserva=" + reserva + ", timestampEnt=" + timestampEnt + ", timestampSal="
                + timestampSal + "]";
    }
}
