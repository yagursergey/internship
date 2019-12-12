package com.syagur.dto;

import javax.validation.constraints.NotNull;

public class RealtyDto {
    private Long id;

    @NotNull
    private Double price;

    @NotNull
    private Double square;
    private String dateOfCreation;

    @NotNull
    private String type;
    private String description;
    private String ownerFirstName;

    public RealtyDto() {
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

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    @Override
    public String toString() {
        return "RealtyDto{" +
                "id=" + id +
                ", price=" + price +
                ", square=" + square +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", ownerFullName='" + ownerFirstName + '\'' +
                '}';
    }
}
