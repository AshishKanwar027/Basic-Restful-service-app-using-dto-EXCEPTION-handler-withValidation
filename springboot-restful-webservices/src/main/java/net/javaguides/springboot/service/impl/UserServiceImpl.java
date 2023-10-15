package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    
    private ModelMapper modelMapper;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        // TODO Auto-generated method stub
        
        //convert user dto into user JPA entity
      // User user=UserMapper.mapToUser(userDto);
   //    User user =modelMapper.map(userDto, User.class);
        
        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exist for user");
        }
        
        User user =AutoUserMapper.MAPPER.mapToUser(userDto);

        
        User savedUser = userRepository.save(user);
        
        //convert user JPA entity to userDto
       // UserDto savedUserDto= UserMapper.maptoUserDto(savedUser);
      // UserDto savedUserDto= modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto=AutoUserMapper.MAPPER.maptToUserDto(savedUser);

       
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userID) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userID).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", userID)
                );
        
         //return UserMapper.maptoUserDto(user);
        // return modelMapper.map(user, UserDto.class);
         return AutoUserMapper.MAPPER.maptToUserDto(user);

    }
    
    
    

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        List<User> users =userRepository.findAll();
        
//        return users.stream().map(UserMapper::maptoUserDto)
//                .collect(Collectors.toList());
//        return users.stream().map((user) ->modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
        return users.stream().map((user) ->AutoUserMapper.MAPPER.maptToUserDto(user))
                .collect(Collectors.toList());
    }
    

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser= userRepository.findById(user.getId()).orElseThrow(
                    ()-> new ResourceNotFoundException("User", "id", user.getId())
                );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        
        //return UserMapper.maptoUserDto(updatedUser);
       // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.maptToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long userID) {
        // TODO Auto-generated method stub
        User existingUser= userRepository.findById(userID).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", userID)
            );
        userRepository.deleteById(userID);
        
    }


}
