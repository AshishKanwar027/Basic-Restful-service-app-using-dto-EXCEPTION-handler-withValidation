package net.javaguides.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    
    private UserService userService;
    
//    //build create User Rest API
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        User savedUser = userService.createUser(user);
//        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
//    }
    
    
    //using DTO for create user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    
    
    
    
    
//    //get user by id
//    @GetMapping("/userID/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long userID){
//        
//        User user = userService.getUserById(userID);
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//        
//    }
    //using Dto
    @GetMapping("/userID/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userID){
        
        UserDto user = userService.getUserById(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
        
    }
    
    
    
    
//    //get all user
//    @GetMapping("allusers")
//    public ResponseEntity<List<User>> getAllUser(){
//        
//        List<User> allUsers = userService.getAllUsers();
//        
//        return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
//        
//    }
    //using dto
    @GetMapping("allusers")
    public ResponseEntity<List<UserDto>> getAllUser(){
        
        List<UserDto> allUsers = userService.getAllUsers();
        
        return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
        
    }
    
    
    
    
    
    
    
    
    
//    //update user
//    @PutMapping("update/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody User user){
//        user.setId(userId);
//        User updatedUser = userService.updateUser(user);
//        return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
//    }
    
    //using Dto
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
          @Valid  @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    
    
    
    

    //delete user
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
    
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResoureNotFoundException(ResourceNotFoundException exception,
//            WebRequest webRequest
//            ){
//        ErrorDetails errorDetails=new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//                );
//        
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//        
//    }

}
