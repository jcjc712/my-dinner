package com.example.mydinner.rest;

import com.example.mydinner.entity.Dish;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {
    @Autowired
    private DishService dishService;

    @GetMapping
    public List<Dish> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/{dishId}")
    public Dish get(@PathVariable Long dishId) {
        Dish dish = dishService.findById(dishId);
        if(dish == null) {
            throw new ResourceRestNotFoundException("Dish not found with id " + dishId);
        }
        return dish;
    }

    @PostMapping
    public Dish add(@Valid @RequestBody Dish dish) {
        dishService.save(dish);
        return dish;
    }

    @PutMapping
    public Dish update(@Valid @RequestBody Dish dish) {
        dishService.save(dish);
        return dish;
    }
}
