package net.javaguides.springboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    
    @NotEmpty(message = "Empty nakko")
    private String firstName;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty
    @Email
    private String email;

}
