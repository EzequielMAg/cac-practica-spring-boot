package com.cac.practicaspringboot.models.DTOs;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter

@Setter
@NoArgsConstructor
public class TransferDTO {
    private Long id;
    private BigDecimal amount;
    private Long sourceAccount;
    private Long targetAccount;
    private LocalDate transferDate;
    private LocalTime transferTime;
}
