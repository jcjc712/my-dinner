package com.example.mydinner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "name")
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "address")
    @Size(min=3, max=100)
    private String address;

    @NotNull
    @NotBlank
    @Column(name = "phone")
    @Size(min=3, max=20)
    private String phone;

    public Customer() {
    }

    public Customer(String email, String name, String address, String phone) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
