package com.cac.practicaspringboot.models;

import com.cac.practicaspringboot.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;

    @Column(name = "account_type")
    private AccountType accountType;

    private String cbu;

    private String alias;

    private BigDecimal amount;

    @ManyToOne // Muchas cuentas para un unico usuario
    private User owner;


//    @ManyToOne
//    private List<Transfer> transfers;

}
