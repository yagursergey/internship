package com.syagur.entity;

import com.syagur.entity.enums.RealtyType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Realty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private Double price;

//    @NotNull
    private Double square;

//    @NotNull
    private LocalDate dateOfCreation;

//    @NotNull
    @Enumerated(value = EnumType.STRING)
    private RealtyType type;
    private String description;

//    @NotNull
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    public Realty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public RealtyType getType() {
        return type;
    }

    public void setType(RealtyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Realty{" +
                "id=" + id +
                ", price=" + price +
                ", square=" + square +
                ", dateOfCreation=" + dateOfCreation +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", isDeleted=" + isDeleted +
                ", owner=" + owner.getEmail() +
                '}';
    }
}
