package com.cac.practicaspringboot.models.DTOs;

import com.cac.practicaspringboot.models.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private AccountType accountType;
    private String cbu;
    private String alias;
    private BigDecimal amount;
    private Long owner;
}
