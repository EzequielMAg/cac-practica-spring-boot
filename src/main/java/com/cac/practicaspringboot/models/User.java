package com.cac.practicaspringboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// La siguiente anotacion, no va a funcionar aun porque debemos importar la libreria de JPA/HIBERNATE
// Hibernate con esta anotacion crea la tabla segun la clase User.
@Entity
@Table(name = "usuarios")
@Getter     // Esta anotacion como la sig, va a generar todos los getters y setters por c/ atributo, gracias a lombok
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    // en este atributo no use la anotacion @Column por lo tanto, hibernate va a crear la columna con el mismo nombre
    private String email;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String surname;

    private String dni;

}
