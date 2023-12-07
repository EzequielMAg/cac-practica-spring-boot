package com.cac.practicaspringboot.models.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDTO {
    private Long id;
    private BigDecimal amount;
    private Long originAccount;
    private Long targetAccount;
    private LocalDate transferDate;
    private LocalTime transferTime;
}
