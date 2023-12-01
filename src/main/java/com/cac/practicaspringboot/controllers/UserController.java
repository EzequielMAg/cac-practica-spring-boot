package com.cac.practicaspringboot.controllers;

import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.nio.file.Path.of;

// Esta anotacion le indica al framework que vamos a trabajar en formato JSON con diferentes clases.
// Nos va a permitir trabajar con metodos del protocolo HTTP y usar status code.
@RestController
@RequestMapping("/api/users")
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
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> lista = this.service.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    // Con @PathVariable se agarra el id pasado en el path
    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        //return ResponseEntity.status(HttpStatusCode.valueOf(200))
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    public void updateAllUser() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }

}
