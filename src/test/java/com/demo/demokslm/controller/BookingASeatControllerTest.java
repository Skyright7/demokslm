package com.demo.demokslm.controller;

import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
import com.demo.demokslm.pojo.StepThreeBookingForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.spring.web.json.Json;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class BookingASeatControllerTest {

    @Autowired
    private SeatDao seatDao;

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

    @AfterEach
    void tearDown() {
    }

    @Test
    void stepOne() throws Exception{
        StepOneBookingForm stepOneBookingForm =new StepOneBookingForm();
        stepOneBookingForm.setArrivingTime(9);
        stepOneBookingForm.setPreferredFloor("");
        stepOneBookingForm.setPreferredArea("");
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/bookingASeat/stepOne")
                        .content(objectMapper.writeValueAsString(stepOneBookingForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        //.andExpect(jsonPath("$.id").value(1)) 验证返回json
        System.out.println(responseString);
    }

    @Test
    void stepTwo() throws Exception{
        int id = 1;
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/bookingASeat/stepTwo/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);
    }

    @Test
    void stepThree() throws Exception{
        //这个要用String与json的互相转换暂时先不搞了
    }
}