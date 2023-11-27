package com.cac.practicaspringboot.controllers;

import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.nio.file.Path.of;

// Esta anotacion le indica al framework que vamos a trabajar en formato JSON con diferentes clases.
// Nos va a permitir trabajar con metodos del protocolo HTTP y usar status code.
@RestController
@RequestMapping("/api")
public class UserController {

    // Definir la url de origen para cada solicitud
    // Para cada mnetodo HTTP permitido debemos realizar una accion
    // Definir el DTO y el Service (Inyeccion de dependencia)

    // CRUD: Crear, Leer, Modificar, ELiminar

    // Generar una instancia del Service (UserService) -> Inyectar una instancia mediante spring boot
    @Autowired  // Esta anotacion le dice al framework spring que esta variable es especial.
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Con @GetMapping cuando un usuario escriba tal url, se va a ejecutar el metodo asociado
    // TODO: Refactorizar el metodo para que retorne un ResponseEntity<List<UserDTO>>
    @GetMapping(value = "/users")
    public List<String> getUsers() {

        List<String> lista = this.service.getUsers();

        //return List.of("Cristiam", "Martina", "Lucas");
        return lista;
    }

    // Con @PathVariable se agarra el id pasado en el path
    @GetMapping(value = "/users/{id}")
    public void getUserById(@PathVariable Long id) {

    }

    @PostMapping(value = "/users")
    public void createUser(@RequestBody UserDTO user) {

    }

    public void updateAllUser() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }

}
