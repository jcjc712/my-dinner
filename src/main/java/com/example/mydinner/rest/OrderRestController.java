package com.example.mydinner.rest;

import com.example.mydinner.entity.Order;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    @Autowired
    private OrderService dishService;

    @GetMapping("/orders")
    public List<Order> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/orders/{dishId}")
    public Order get(@PathVariable Long dishId) {
        Order dish = dishService.findById(dishId);
        if(dish == null) {
            throw new ResourceRestNotFoundException("Dish not found with id " + dishId);
        }
        return dish;
    }

    @PostMapping("/orders")
    public Order add(@Valid @RequestBody Order order) {
        dishService.save(order);
        return order;
    }
}
