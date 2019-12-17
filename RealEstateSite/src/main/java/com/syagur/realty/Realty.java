package com.syagur.realty;

import com.syagur.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
public class Realty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private Double square;

    private LocalDate dateOfCreation;

    @Enumerated(value = EnumType.STRING)
    private RealtyType type;
    private String description;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

}
