package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public interface UserService {
    
    UserDto createUser(UserDto user);
    
    UserDto getUserById(Long userID);
    
    List<UserDto> getAllUsers();
    
    UserDto updateUser(UserDto user);
    
    void deleteUser(Long userID);

}
