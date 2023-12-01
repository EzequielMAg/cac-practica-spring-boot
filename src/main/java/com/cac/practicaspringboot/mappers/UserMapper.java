package com.cac.practicaspringboot.mappers;

import com.cac.practicaspringboot.models.DTOs.UserDTO;
import com.cac.practicaspringboot.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User dtoToUser(UserDTO dto) {
        User user = new User();

        user.setName();
        user.setSurname();
        user.setDni();
        user.setEmail();
        user.setPassword();

    }

}
