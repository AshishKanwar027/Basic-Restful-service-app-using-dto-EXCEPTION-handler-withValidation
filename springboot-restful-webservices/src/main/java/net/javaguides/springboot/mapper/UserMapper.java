package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {

    //convert jpa entity to userdto
    public static UserDto maptoUserDto(User user) {
        UserDto userDto=new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
                );
        return userDto;
        
    }
    
    
    //convert userDto to User jpa entity
    public static User mapToUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
                );
        return user;
    }
}
