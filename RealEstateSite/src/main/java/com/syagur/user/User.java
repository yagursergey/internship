package com.syagur.user;

import com.syagur.realty.Realty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "realties")
@EqualsAndHashCode(exclude = "realties")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Size(min = 2, max = 30, message = "First Name must have length between 2 and 30")
    private String firstName;

    @Size(min = 2, max = 30, message = "Second Name must have length between 2 and 30")
    private String secondName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Realty> realties;

}
