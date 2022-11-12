package com.demo.demokslm.controller;

import com.demo.demokslm.dao.SwiperDao;
import com.demo.demokslm.pojo.Swiper;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class SwiperControllerTest {
    @Autowired
    private SwiperDao swiperDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findSwiper() throws Exception{
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        assert(swiperDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/swiper")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        swiperDao.deleteById(999);
        assert(swiperDao.selectById(999)==null);
    }

    @Test
    void addSwiper() throws Exception{
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/swiper").content(objectMapper.writeValueAsString(swiper))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(swiperDao.selectById(999)!=null);
        //删除测试用数据
        swiperDao.deleteById(999);
        assert(swiperDao.selectById(999)==null);
    }

    @Test
    void adjustSwiper() throws Exception{
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        assert(swiperDao.selectById(999)!=null);
        Swiper swiper1 = swiperDao.selectById(999);
        assert(swiper1.getImageId()==9);
        swiper.setImageId(10);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/swiper").content(objectMapper.writeValueAsString(swiper))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        Swiper swiper2 = swiperDao.selectById(999);
        assert(swiper2.getImageId()==10);

        swiperDao.deleteById(999);
        assert(swiperDao.selectById(999)==null);
    }

    @Test
    void deleteSwiperById() throws Exception{
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        assert(swiperDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/swiper/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(swiperDao.selectById(999)==null);
    }
}