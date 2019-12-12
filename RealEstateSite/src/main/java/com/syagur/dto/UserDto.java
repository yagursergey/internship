package com.syagur.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
