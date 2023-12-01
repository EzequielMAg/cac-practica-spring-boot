package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.models.User;
import com.cac.practicaspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Inyectar una isntancia del repositorio
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // metodos
    public List<String> getUsers() {
        return List.of("Cristian", "Luis", "Alejandra");
    }

    public UserDTO createUser(UserDTO user) {
        return repository.save(user);
    }
}
