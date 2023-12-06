package com.cac.practicaspringboot.controllers;

import com.cac.practicaspringboot.exceptions.UserNotExistsException;
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
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        //return ResponseEntity.status(HttpStatusCode.valueOf(200))
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateAllUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAllUser(id, user));
    }

    // EL PROFE BORRO ESTE METODO PATCH y dijo que para nuestro proyecto, es suficiente con el metodo de arriba.
    // Ya que o modifico todos los campos o solo modifico alguno, pero en el servicio, tal logica es del PATCH
    // Pero para hacerlo de forma correcta, y separar la responsabilidad, en el servicio, deberiamos sacar la logica
    // del patch y hacer su metodo correspondiente, ya que un PATCH es para MODIFICAR UNA ENTITY DE FORMA PARCIAL.
    // Y el PUT es para MODIFICAR COMPLETAMENTE un objeto, Y SE DEBE MODIFICAR TODx EL USUARIO CON TODOS LOS ATRIBUTOS
    // DEL DTO. Entonces debo restringir desde otro lado que unicamente se va a ejecutar el update completo si vienen
    // todos los campos del DTO, osea ningun campo del userDto tiene que venir nulo, excepto el ID.
    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }

    // Metodo para validar caracteres del email

}
