package com.cac.practicaspringboot.repositories;

import com.cac.practicaspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // En JpaRepository ya tenemos todos los metodos necesarios para el CRUD
    // Tenemos metodos a traves del ID que es la primary key, pero no segun los demas atributos, pero
    // Podemos crearlos de la siguiente forma, por ejemplo para el email, u algun otro atributo, pero
    // siempre y cuando sea respecto a un atributo de la entity

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
