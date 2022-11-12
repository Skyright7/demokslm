package com.demo.demokslm.controller;

import com.demo.demokslm.dao.EventDao;
import com.demo.demokslm.pojo.EventCard;
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
class EventCardControllerTest {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findEventCardList() throws Exception{
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);
    }


    @Test
    void findOneEventCardById() throws Exception{
        int id = 1;
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/event/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);
    }

    @Test
    void findEventCardIdList() throws Exception{
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/event/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);
    }

    @Test
    void addEventCard() throws Exception{
        assert(eventDao.selectById(999)==null);
        EventCard eventCard = new EventCard();
        eventCard.setEventId(999);
        eventCard.setHeader("233");
        eventCard.setDetail("2233");
        eventCard.setSnapshot("2");
        eventCard.setImageId(1);
        eventCard.setHoldTime("2020/02/02");
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/event").content(objectMapper.writeValueAsString(eventCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
        assert(eventDao.selectById(999)!=null);
        EventCard eventCard1 = eventDao.selectById(999);

        assert(eventCard1.getEventId()==999);
        assert (eventCard1.getDetail().equals("2233"));
        assert (eventCard1.getHeader().equals("233"));
        assert (eventCard1.getSnapshot().equals("2"));
        assert (eventCard1.getImageId()==1);
        assert (eventCard1.getHoldTime().equals("2020/02/02"));

        eventDao.deleteById(999);
    }
    @Test
    void adjustEventCard() throws Exception{
        assert(eventDao.selectById(999)==null);
        EventCard eventCard = new EventCard();
        eventCard.setEventId(999);
        eventCard.setHeader("233");
        eventCard.setDetail("2233");
        eventCard.setSnapshot("2");
        eventCard.setImageId(1);
        eventCard.setHoldTime("2020/02/02");
        eventDao.insert(eventCard);
        EventCard eventCard1 = eventDao.selectById(999);
        assert(eventCard1.getEventId()==999);
        assert (eventCard1.getDetail().equals("2233"));
        assert (eventCard1.getHeader().equals("233"));
        assert (eventCard1.getSnapshot().equals("2"));
        assert (eventCard1.getImageId()==1);
        assert (eventCard1.getHoldTime().equals("2020/02/02"));

        eventCard.setHoldTime("2021/03/03");
        eventCard.setImageId(3);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/event").content(objectMapper.writeValueAsString(eventCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        EventCard eventCard2 = eventDao.selectById(999);
        assert(eventCard2.getEventId()==999);
        assert(eventCard2.getHoldTime().equals("2021/03/03"));
        assert(eventCard2.getImageId()==3);

        eventDao.deleteById(999);
    }

    @Test
    void deleteEventCardById() throws Exception{
        assert(eventDao.selectById(999)==null);
        EventCard eventCard = new EventCard();
        eventCard.setEventId(999);
        eventCard.setHeader("233");
        eventCard.setDetail("2233");
        eventCard.setSnapshot("2");
        eventCard.setImageId(1);
        eventCard.setHoldTime("2020/02/02");
        eventDao.insert(eventCard);
        assert(eventDao.selectById(999)!=null);
        int id = 999;

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/event/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
        assert(eventDao.selectById(999)==null);
    }
}