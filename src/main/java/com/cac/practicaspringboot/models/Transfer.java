package com.cac.practicaspringboot.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Getter
@Setter
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfers")
    private Long id;

    private BigDecimal amount;

    @Column(name = "origin_account")
    private Long originAccount;

    @Column(name = "target_account")
    private Long targetAccount;

    @Column(name = "transfer_date")
    private LocalDate transferDate;

    @Column(name = "transfer_time")
    private LocalTime transferTime;
}