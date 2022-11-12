package com.demo.demokslm.controller;

import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
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
class SeatControllerTest {
    @Autowired
    private SeatDao seatDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findSeat() throws Exception{
        assert (seatDao.selectById(999)==null);
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatDao.insert(seat);
        assert (seatDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/seat/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        seatDao.deleteById(999);
        assert (seatDao.selectById(999)==null);
    }

    @Test
    void addSeat() throws Exception{
        assert (seatDao.selectById(999)==null);
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/seat").content(objectMapper.writeValueAsString(seat))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(seatDao.selectById(999)!=null);
        seatDao.deleteById(999);
        assert (seatDao.selectById(999)==null);
    }

    @Test
    void adjustSeat() throws Exception{
        assert (seatDao.selectById(999)==null);
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatDao.insert(seat);
        assert (seatDao.selectById(999)!=null);
        assert(seatDao.selectById(999).getSeatOccupancy() == 4);
        assert(seatDao.selectById(999).getSeatType() == 9);
        seat.setSeatOccupancy(5);
        seat.setSeatType(10);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/seat").content(objectMapper.writeValueAsString(seat))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(seatDao.selectById(999).getSeatOccupancy() == 5);
        assert(seatDao.selectById(999).getSeatType() == 10);
        seatDao.deleteById(999);
        assert (seatDao.selectById(999)==null);
    }

    @Test
    void deleteSeatById() throws Exception{
        assert (seatDao.selectById(999)==null);
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatDao.insert(seat);
        assert (seatDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/seat/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(seatDao.selectById(999) == null);
    }
}