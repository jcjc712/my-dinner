package com.example.mydinner.service;

import com.example.mydinner.dao.OrderRepository;
import com.example.mydinner.entity.Dish;
import com.example.mydinner.entity.Order;
import com.example.mydinner.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishService dishService;

    @Transactional
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Transactional
    public Order findById(Long id) {
        Optional<Order> option = this.orderRepository.findById(id);
        Order customer = null;

        if(option.isPresent()) {
            customer = option.get();
        }

        return customer;
    }

    @Transactional
    public void save(Order order) {
        for(OrderDetail detail: order.getOrderDetails()) {
            Dish dish = dishService.findById(detail.getDish().getId());
            detail.setDish(dish);
        }

        this.orderRepository.save(order);
    }

    @Transactional
    public void deleteById(Long id) {
        this.orderRepository.deleteById(id);
    }
}
