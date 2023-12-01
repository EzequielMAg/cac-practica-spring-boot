package com.cac.practicaspringboot.mappers;

import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User dtoToUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setDni(dto.getDni());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
    public static UserDTO userToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setDni(user.getDni());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }

}
