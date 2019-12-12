package com.syagur.entity;

import com.syagur.entity.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

//    @NotNull
    @Size(min = 2, max = 30, message = "First Name must have length between 2 and 30")
    private String firstName;

//    @NotNull
    @Size(min = 2, max = 30, message = "Second Name must have length between 2 and 30")
    private String secondName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

//    @NotNull
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Realty> realties;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Realty> getRealties() {
        return realties;
    }

    public void setRealties(List<Realty> realties) {
        this.realties = realties;
    }

    @Override
    public String toString() {
        return "User:   {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role + "\n" +
                '}';
    }
}
