package com.example.mydinner.service;

import com.example.mydinner.dao.DishRepository;
import com.example.mydinner.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public List<Dish> findAll() {
        return this.dishRepository.findAll();
    }

    @Transactional
    public Dish findById(Long id) {
        Optional<Dish> option = this.dishRepository.findById(id);
        Dish customer = null;

        if(option.isPresent()) {
            customer = option.get();
        }

        return customer;
    }

    @Transactional
    public void save(Dish dish) {
        this.dishRepository.save(dish);
    }

    @Transactional
    public void deleteById(Long id) {
        this.dishRepository.deleteById(id);
    }
}
