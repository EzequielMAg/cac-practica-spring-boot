package com.cac.practicaspringboot.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor // Esta anotacion va a generar todas las combniaciones de constructores segun los atributos
@NoArgsConstructor  // Esta genera un constructor vacio
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String dni;


}
