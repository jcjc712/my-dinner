package com.example.mydinner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

//    @Column(name="dish_id")
//    private Long dishId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price")
    private Double price = null;

    @Column(name="subtotal")
    private Double subtotal = 0.0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Dish dish;

    public void setDish(Dish dish) {
        this.dish = dish;
        this.price = dish.getPrice();

        if(this.quantity != null) {
            this.subtotal = (double) this.quantity * this.price;
        }
    }

    public Dish getDish() {
        return dish;
    }

    public OrderDetail() {}

    public OrderDetail(Long id, Integer quantity, Double price, Double subtotal) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getDishId() {
//        return dishId;
//    }
//
//    public void setDishId(Long dishId) {
//        this.dishId = dishId;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;

        if(this.price != null) {
            this.subtotal = (double) this.quantity * this.price;
        }
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
