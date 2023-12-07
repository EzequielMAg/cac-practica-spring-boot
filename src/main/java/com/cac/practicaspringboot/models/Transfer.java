package com.cac.practicaspringboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Getter
@Setter
@Table(name = "transferencias")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long id;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "cuenta_origen")
    private Long sourceAccount;

    @Column(name = "cuenta_destino")
    private Long targetAccount;

    @Column(name = "fecha_transferencia")
    private LocalDate transferDate;

    @Column(name = "hora_trandferencia")
    private LocalTime transferTime;
}