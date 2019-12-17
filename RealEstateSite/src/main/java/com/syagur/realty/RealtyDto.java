package com.syagur.realty;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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

}
