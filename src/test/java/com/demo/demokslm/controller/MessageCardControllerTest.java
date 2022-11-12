package com.demo.demokslm.controller;

import com.demo.demokslm.dao.MessageDao;
import com.demo.demokslm.pojo.MessageCard;
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
class MessageCardControllerTest {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void findMyMessageCardList() throws Exception{
        //user 999 and 1000 is using to test
        assert(messageDao.selectById(999)==null);
        MessageCard messageCard = new MessageCard();
        messageCard.setMessageId(999);
        messageCard.setDetail("233");
        messageCard.setHeader("2233");
        messageCard.setSender("administer");
        messageCard.setSnapshot("2");
        messageCard.setImageId(1);
        messageCard.setReceiverId(999);
        messageCard.setSendTime("2022/10/10");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(999)!=null);

        assert(messageDao.selectById(1000)==null);
        messageCard.setMessageId(1000);
        messageCard.setDetail("123");
        messageCard.setHeader("1234");
        messageCard.setSender("administer");
        messageCard.setSnapshot("1");
        messageCard.setImageId(3);
        messageCard.setReceiverId(999);
        messageCard.setSendTime("2022/11/11");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(1000)!=null);


        assert(messageDao.selectById(1001)==null);
        messageCard.setMessageId(1001);
        messageCard.setDetail("456");
        messageCard.setHeader("4567");
        messageCard.setSender("administer");
        messageCard.setSnapshot("4");
        messageCard.setImageId(2);
        messageCard.setReceiverId(1000);
        messageCard.setSendTime("2022/9/9");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(1001)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/message/list/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);


        messageDao.deleteById(999);
        messageDao.deleteById(1000);
        messageDao.deleteById(1001);
        assert(messageDao.selectById(999)==null);
        assert(messageDao.selectById(1000)==null);
        assert(messageDao.selectById(1001)==null);
    }

    @Test
    void findOneMessageCardById() throws Exception{
        assert(messageDao.selectById(999)==null);
        MessageCard messageCard = new MessageCard();
        messageCard.setMessageId(999);
        messageCard.setDetail("233");
        messageCard.setHeader("2233");
        messageCard.setSender("administer");
        messageCard.setSnapshot("2");
        messageCard.setImageId(1);
        messageCard.setReceiverId(3);
        messageCard.setSendTime("2022/10/10");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/message/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        //删除测试用数据
        messageDao.deleteById(999);
        assert(messageDao.selectById(999)==null);
    }

    @Test
    void addMessageCard() throws Exception{
        assert(messageDao.selectById(999)==null);
        MessageCard messageCard = new MessageCard();
        messageCard.setMessageId(999);
        messageCard.setDetail("233");
        messageCard.setHeader("2233");
        messageCard.setSender("administer");
        messageCard.setSnapshot("2");
        messageCard.setImageId(1);
        messageCard.setReceiverId(3);
        messageCard.setSendTime("2022/10/10");

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/message").content(objectMapper.writeValueAsString(messageCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        MessageCard messageCard1 = messageDao.selectById(999);
        assert (messageCard1.getMessageId()==999);
        assert (messageCard1.getDetail().equals("233"));
        assert (messageCard1.getHeader().equals("2233"));
        assert (messageCard1.getSender().equals("administer"));
        assert (messageCard1.getSnapshot().equals("2"));
        assert (messageCard1.getImageId()==1);
        assert (messageCard1.getReceiverId()==3);
        assert (messageCard1.getSendTime().equals("2022/10/10"));

        //删除测试用数据
        messageDao.deleteById(999);
        assert(messageDao.selectById(999)==null);
    }

    @Test
    void adjustMessageCard() throws Exception{
        assert(messageDao.selectById(999)==null);
        MessageCard messageCard = new MessageCard();
        messageCard.setMessageId(999);
        messageCard.setDetail("233");
        messageCard.setHeader("2233");
        messageCard.setSender("administer");
        messageCard.setSnapshot("2");
        messageCard.setImageId(1);
        messageCard.setReceiverId(3);
        messageCard.setSendTime("2022/10/10");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(999)!=null);
        MessageCard messageCard1 = messageDao.selectById(999);
        assert (messageCard1.getSendTime().equals("2022/10/10"));
        assert (messageCard1.getImageId()==1);
        messageCard.setImageId(3);
        messageCard.setSendTime("2022/11/11");

        String responseString = mockMvc.perform(MockMvcRequestBuilders.put("/message").content(objectMapper.writeValueAsString(messageCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        MessageCard messageCard2 = messageDao.selectById(999);
        assert (messageCard2.getSendTime().equals("2022/11/11"));
        assert (messageCard2.getImageId()==3);


        messageDao.deleteById(999);
        assert(messageDao.selectById(999)==null);
    }

    @Test
    void deleteMessageCardById() throws Exception{
        assert(messageDao.selectById(999)==null);
        MessageCard messageCard = new MessageCard();
        messageCard.setMessageId(999);
        messageCard.setDetail("233");
        messageCard.setHeader("2233");
        messageCard.setSender("administer");
        messageCard.setSnapshot("2");
        messageCard.setImageId(1);
        messageCard.setReceiverId(3);
        messageCard.setSendTime("2022/10/10");
        messageDao.insert(messageCard);
        assert(messageDao.selectById(999)!=null);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.delete("/message/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);

        assert(messageDao.selectById(999)==null);
    }
}