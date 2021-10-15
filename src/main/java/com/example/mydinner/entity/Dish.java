package com.example.mydinner.entity;

import com.example.mydinner.service.validation.StringEnumeration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "dishes")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NotNull
    @NotBlank
    @Column
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @NotBlank
    @Column
    @Size(min=3, max=100)
    private String description;

    @NotNull
    @Column
    private Double price;

    public enum CuisineTypes { mexican, italian, japanese }

    @NotNull
    @NotBlank
    @StringEnumeration(enumClass = CuisineTypes.class, message = "Only 'mexican', 'italian' and 'japanese' are accepted values")
    @Column
    private String cuisine;

    public enum StatusTypes { available, disable }

    @NotNull
    @NotBlank
    @StringEnumeration(enumClass = StatusTypes.class, message = "Only 'available' and 'disable' are accepted values")
    @Column
    private String status;

    public Dish() {
    }

    public Dish(Long id, String name, String description, Double price, String cuisine, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cuisine = cuisine;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
