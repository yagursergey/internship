package com.syagur.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;

    @Email
    private String email;

    @Size(min = 4, max = 20, message = "Password must have length between 4 and 20")
    private String password;


    @Size(min = 2, max = 30, message = "First Name must have length between 2 and 30")
    private String firstName;


    @Size(min = 2, max = 30, message = "Second Name must have length between 2 and 30")
    private String secondName;

    private String role;

}
