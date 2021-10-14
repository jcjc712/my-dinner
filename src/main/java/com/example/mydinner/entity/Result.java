package com.example.mydinner.entity;

public class Result {
    private Long quantity;

    private String cuisine;

    public Result() {
    }

    public Result(Long quantity, String cuisine) {
        this.quantity = quantity;
        this.cuisine = cuisine;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
