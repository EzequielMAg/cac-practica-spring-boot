package com.cac.practicaspringboot.models;

import com.cac.practicaspringboot.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long id;

    @Column(name = "tipo_cuenta")
    private AccountType accountType;

    private String cbu;

    private String alias;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "dueño")
    private Long owner;

    //@ManyToOne(
    //private List<Transfer> transfers;

}
