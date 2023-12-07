package com.cac.practicaspringboot.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// La siguiente anotacion, no va a funcionar aun porque debemos importar la libreria de JPA/HIBERNATE
// Hibernate con esta anotacion crea la tabla segun la clase User.
@Entity
@Table(name = "users")
@Getter     // Esta anotacion como la sig, va a generar todos los getters y setters por c/ atributo, gracias a lombok
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Este patron necesita que existan los diferentes constructores, x eso agregamos las 2 anotaciones anteriores.
         // Este patron genera objetitos con las caracteristicas que nosotros le digamos, sin generar la instancia
         // nosotros mismos, es decir sin usar el operador new.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    // en este atributo no use la anotacion @Column por lo tanto, hibernate va a crear la columna con el mismo nombre
    private String email;

    private String password;

    private String name;

    private String surname;

    private String dni;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
}
