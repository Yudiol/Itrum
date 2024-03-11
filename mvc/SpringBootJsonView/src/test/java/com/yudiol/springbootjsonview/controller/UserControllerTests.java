package com.yudiol.springbootjsonview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yudiol.springbootjsonview.model.Order;
import com.yudiol.springbootjsonview.model.User;
import com.yudiol.springbootjsonview.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    private final User user = new User();

    private final Order order1 = new Order();
    private final Order order2 = new Order();

    @BeforeEach
    public void setup() {
        user.setUserId(1L);
        user.setName("Jon Snow");
        user.setEmail("Jon@mail.com");

        order1.setOrderId(2L);
        order1.setName("Phone");
        order1.setTotalAmount(11);
        order1.setStatus("active");

        order2.setOrderId(3L);
        order2.setName("Box");
        order2.setTotalAmount(22);
        order2.setStatus("cancelled");

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        user.setOrders(orders);
    }


    @Test
    void getAllUsers() throws Exception {
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userService.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].name").value("Jon Snow"));

        verify(userService, times(1)).findAll();
    }

    @Test
    void getUserById() throws Exception {

        String userToString = objectMapper.writeValueAsString(user);
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Jon Snow"))
                .andExpect(jsonPath("$.email").value("Jon@mail.com"))
                .andExpect(jsonPath("$.orders[0].orderId").value(2))
                .andExpect(jsonPath("$.orders[0].name").value("Phone"))
                .andExpect(jsonPath("$.orders[0].totalAmount").value(11))
                .andExpect(jsonPath("$.orders[0].status").value("active"))
                .andExpect(jsonPath("$.orders[1].orderId").value(3))
                .andExpect(jsonPath("$.orders[1].name").value("Box"))
                .andExpect(jsonPath("$.orders[1].totalAmount").value(22))
                .andExpect(jsonPath("$.orders[1].status").value("cancelled"))
        ;

        verify(userService, times(1)).findById(anyLong());
    }

    @Test
    public void createUser() throws Exception {

        when(userService.create(user)).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Jon Snow"))
                .andExpect(jsonPath("$.email").value("Jon@mail.com"));

        verify(userService, times(1)).create(user);
    }

    @Test
    public void updateUser() throws Exception {

        when(userService.update(1L, user)).thenReturn(user);

        mockMvc.perform(patch("/api/v1/users/1")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Jon Snow"))
                .andExpect(jsonPath("$.email").value("Jon@mail.com"));


        verify(userService, times(1)).update(1L, user);
    }

    @Test
    public void deleteUser() throws Exception {
        doNothing().when(userService).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());
    }
}


