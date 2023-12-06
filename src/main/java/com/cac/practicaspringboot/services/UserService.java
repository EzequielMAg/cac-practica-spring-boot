package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.exceptions.UserNotExistsException;
import com.cac.practicaspringboot.mappers.UserMapper;
import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.models.User;
import com.cac.practicaspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserDTO createUser(UserDTO userDto) {

        User userValidated = validateUserByEmail(userDto.getEmail());
        if(userValidated == null) {

            User userSaved = repository.save(UserMapper.dtoToUser(userDto));
            return UserMapper.userToDto(userSaved);
        }

        throw new UserNotExistsException("Usuario con email " + userDto.getEmail() + " ya existe!");

    }

    public UserDTO getUserById(Long id) {
        // Obtengo el usuario de la DB segun su id
        User entity = repository.findById(id).get();

        return UserMapper.userToDto(entity);
    }

    public String deleteUser(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "El usuario con id " + id + " ha sido eliminado!";
        } else {
            throw new UserNotExistsException("El usuario a eliminar elegido no existe!");
        }
    }
    
    public UserDTO updateUser(Long id, UserDTO dto) {
        // Primero verifico si existe un usuario con ese id en la BD
        if(repository.existsById(id)) {

            // Consigo el usuario a modificar desde la BD
            User userToModify = repository.findById(id).get();

            // Validar que datos no vienen en null para modificar el objeto traido de la BD
            // LOGICA DEL PATCH|
            if(dto.getEmail() != null)
                userToModify.setEmail(dto.getEmail());

            if(dto.getPassword() != null)
                userToModify.setPassword(dto.getPassword());

            if(dto.getName() != null)
                userToModify.setName(dto.getName());

            if(dto.getSurname() != null)
                userToModify.setSurname(dto.getSurname());

            if(dto.getDni() != null)
                userToModify.setDni(dto.getDni());

            // Persistimos la modificacion del usuario en la BD
            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }
        return null;
    }

    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(String email) {
        //TODO: quiero refactorizar esto para retonar un boolean, entonces usaria el otro metodo
        // del repositorio: existsByEmail()
        return repository.findByEmail(email).get();
    }


}
