package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.exceptions.UserNotExistsException;
import com.cac.practicaspringboot.mappers.UserMapper;
import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.models.User;
import com.cac.practicaspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    // Inyectar una isntancia del repositorio
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // metodos
    public List<UserDTO> getUsers() {
        // Obtengo la lista de la entidad usuario de la DB
        List<User> users = repository.findAll();

        //Mapea cada usuario de la lista hacia un DTO
        List<UserDTO> usersDtos = users.stream()
                .map(UserMapper::userToDto) //Operador de ambito (::).Por cada elemento ejecuta de tal clase tal metodo
                .collect(Collectors.toList());

        return usersDtos;
    }


    public UserDTO createUser(UserDTO userDTO) {
        User user = repository.save(UserMapper.dtoToUser(userDTO));
        return UserMapper.userToDto(user);
    }

    public UserDTO getUserById(Long id) {
        // Obtengo el usuario de la DB segun su id
        User entity = repository.findById(id).get();

        return UserMapper.userToDto(entity);
    }

    public String deleteUser(Long id) {
        if(this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return "El usuario con id " + id + " ha sido eliminado!";
        } else {
            throw new UserNotExistsException("El usuario a eliminar elegido no existe!");
        }
    }
}
