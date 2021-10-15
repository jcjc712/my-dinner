package com.example.mydinner.rest;

import com.example.mydinner.dao.SoldDishesRepository;
import com.example.mydinner.entity.Order;
import com.example.mydinner.entity.Result;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    @Autowired
    private OrderService dishService;

    @Autowired
    private SoldDishesRepository soldDishesRepository;

    @GetMapping
    public List<Order> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/sold-orders")
    public List<Result> soldOrders(@RequestParam String from, @RequestParam String to) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        List<Result> result = new ArrayList<>();

        try {
            Date fromDate = dateFormat.parse(from);
            Date toDate = dateFormat.parse(to);
            Timestamp fromT = new java.sql.Timestamp(fromDate.getTime());
            Timestamp toT = new java.sql.Timestamp(toDate.getTime());
            result = soldDishesRepository.findAll(fromT, toT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @PostMapping
    public Order add(@Valid @RequestBody Order order) {
        dishService.save(order);
        return order;
    }

    @GetMapping("/{orderId}")
    public Order get(@PathVariable Long orderId) {
        Order order = dishService.findById(orderId);
        if(order == null) {
            throw new ResourceRestNotFoundException("Dish not found with id " + orderId);
        }
        return order;
    }
}
