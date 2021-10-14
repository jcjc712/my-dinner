package com.example.mydinner.rest;

import com.example.mydinner.dao.SoldDishesRepository;
import com.example.mydinner.entity.Dish;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class DishRestController {
    @Autowired
    private DishService dishService;

    @Autowired
    private SoldDishesRepository soldDishesRepository;

    @GetMapping("/foobar")
    public List<?> foobar(@RequestParam String from, @RequestParam String to) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<?> result = new ArrayList<>();

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

    @GetMapping("/dishes")
    public List<Dish> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/dishes/{dishId}")
    public Dish get(@PathVariable Long dishId) {
        Dish dish = dishService.findById(dishId);
        if(dish == null) {
            throw new ResourceRestNotFoundException("Dish not found with id " + dishId);
        }
        return dish;
    }

    @PostMapping("/dishes")
    public Dish add(@Valid @RequestBody Dish dish) {
        // todo validate enums
        dishService.save(dish);
        return dish;
    }

    @PutMapping("/dishes")
    public Dish update(@Valid @RequestBody Dish dish) {
        dishService.save(dish);
        return dish;
    }

    @DeleteMapping("/dishes/{dishId}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long dishId) {
        Dish dish = dishService.findById(dishId);
        if(dish == null) {
            throw new ResourceRestNotFoundException("Dish not found with id " + dishId);
        }
        dishService.deleteById(dishId);
        Map<String, String> message = new HashMap<>();
        message.put("message", String.format("Dish %s deleted", dishId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
