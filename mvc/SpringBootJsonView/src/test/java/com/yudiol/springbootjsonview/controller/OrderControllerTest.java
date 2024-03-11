package com.yudiol.springbootjsonview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yudiol.springbootjsonview.model.Order;
import com.yudiol.springbootjsonview.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    Order order = new Order();

    @BeforeEach
    public void setup() {

        order.setOrderId(2L);
        order.setName("Phone");
        order.setTotalAmount(3);
        order.setStatus("active");
    }

    @Test
    public void getById() throws Exception {

        when(orderService.getById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(2))
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.totalAmount").value(3))
                .andExpect(jsonPath("$.status").value("active"));

        verify(orderService, times(1)).getById(1L);
    }

    @Test
    public void create() throws Exception {

        when(orderService.create(order)).thenReturn(order);

        mockMvc.perform(post("/api/v1/orders")
                        .content(objectMapper.writeValueAsString(order))
                        .contentType("application/json"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.orderId").value(2))
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.totalAmount").value(3))
                .andExpect(jsonPath("$.status").value("active"));

        verify(orderService, times(1)).create(order);
    }

    @Test
    public void update() throws Exception {

        when(orderService.update(2L, order)).thenReturn(order);

        mockMvc.perform(patch("/api/v1/orders/2")
                        .content(objectMapper.writeValueAsString(order))
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(2))
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.totalAmount").value(3))
                .andExpect(jsonPath("$.status").value("active"))
        ;

        verify(orderService, times(1)).update(2L, order);
    }

    @Test
    public void deleteById() throws Exception {

        doNothing().when(orderService).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/orders/1"))
                .andExpect(status().isOk());
    }

}