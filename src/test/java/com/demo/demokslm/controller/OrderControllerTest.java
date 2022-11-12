package com.demo.demokslm.controller;

import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.pojo.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class OrderControllerTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findOrder() throws Exception{
        assert (orderDao.selectById(999)==null);
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/order/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);


        orderDao.deleteById(999);
    }

    @Test
    void addOrder() throws Exception{
        assert (orderDao.selectById(999)==null);
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/order").content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(orderDao.selectById(999)!=null);
        orderDao.deleteById(999);
    }

    @Test
    void adjustOrder() throws Exception{
        assert (orderDao.selectById(999)==null);
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderDao.insert(order);
        assert(orderDao.selectById(999)!=null);
        assert (orderDao.selectById(999).getOrderStatus()==2);
        assert (orderDao.selectById(999).getCustomId()==1);
        order.setOrderStatus(3);
        order.setCustomId(2);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/order").content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(orderDao.selectById(999).getCustomId() == 2);
        assert(orderDao.selectById(999).getOrderStatus() == 3);
        orderDao.deleteById(999);
    }

    @Test
    void deleteOrderById() throws Exception{
        assert (orderDao.selectById(999)==null);
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderDao.insert(order);
        assert(orderDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/order/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(orderDao.selectById(999) == null);
    }

    @Test
    void findOrderByQrcode() throws Exception{
        assert (orderDao.selectById(999)==null);
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(1);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderDao.insert(order);
        assert(orderDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/order/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);


        orderDao.deleteById(999);
        assert(orderDao.selectById(999) == null);
    }
}