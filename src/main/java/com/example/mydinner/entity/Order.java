package com.example.mydinner.entity;

import com.example.mydinner.service.validation.AtLeastTwoElements;
import com.example.mydinner.service.validation.ValidCustomer;
import com.example.mydinner.service.validation.ValidOrderDateTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Email
    @ValidCustomer
    @Column(name="customer_email")
    private String customerEmail;

    @ValidOrderDateTime
    @Column(name = "ordered_at")

    private Timestamp orderedAt = Timestamp.valueOf(ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime());

    @Size(min=3, max=100)
    @Column
    private String address;

    @Column
    private Double total = 0.0;

    @Valid
    @AtLeastTwoElements
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Order() {
    }

    public Order(Long id, String customerEmail, Timestamp orderedAt, String address, Double total) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.orderedAt = orderedAt;
        this.address = address;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Timestamp getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Timestamp orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;

        for(OrderDetail detail : orderDetails) {
            detail.setOrder(this);
        }
    }
}
