package com.example.mydinner.rest;

import com.example.mydinner.entity.Customer;
import com.example.mydinner.entity.Dish;
import com.example.mydinner.entity.Order;
import com.example.mydinner.entity.OrderDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Customer customer;
    private Dish dish1;
    private Dish dish2;

    @BeforeEach
    public void setup() throws Exception {
        // Create a customer
        RequestBuilder createCustomerRequest = MockMvcRequestBuilders
                .post("/api/customers")
                .content(asJsonString(new Customer("test@test10", "firstName4", "lastName4", "5555555")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult customerResult = mockMvc.perform(createCustomerRequest)
                .andExpect(status().is(200))
                .andReturn();

        this.customer = new ObjectMapper().readValue(customerResult.getResponse().getContentAsString(), Customer.class);

        // Create a dish
        RequestBuilder createDishRequest1 = MockMvcRequestBuilders
                .post("/api/dishes")
                .content(asJsonString(new Dish("Sushi test", "Nice", 205.2, "japanese", "available")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult dish1Result = this.mockMvc.perform(createDishRequest1)
                .andExpect(status().is(200))
                .andReturn();
        this.dish1 = new ObjectMapper().readValue(dish1Result.getResponse().getContentAsString(), Dish.class);
        RequestBuilder createDishRequest2 = MockMvcRequestBuilders
                .post("/api/dishes")
                .content(asJsonString(new Dish("Tacos test", "Nice", 50.3, "mexican", "available")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult dish2Result = this.mockMvc.perform(createDishRequest2)
                .andExpect(status().is(200))
                .andReturn();

        this.dish2 = new ObjectMapper().readValue(dish2Result.getResponse().getContentAsString(), Dish.class);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createOrder() throws Exception {
        Order order = new Order(customer.getEmail(), "Av. Simepre Viva");
        Set<OrderDetail> details = new HashSet<>();
        details.add(new OrderDetail(4, dish1.getId()));
        details.add(new OrderDetail(7, dish2.getId()));
        order.setOrderDetails(details);
        System.out.println(asJsonString(order));
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/orders")
                .content("{\"customerEmail\":"+customer.getEmail()+",\"orderedAt\":1634331050907,\"address\":\"Av. Simepre Viva\",\"orderDetails\":[{\"quantity\":4,\"dishIdentifier\":"+dish1.getId()+"},{\"quantity\":7,\"dishIdentifier\":"+dish2.getId()+"}]}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(request);
    }
}
