package com.demo.demokslm.service;

import com.demo.demokslm.dao.MessageDao;
import com.demo.demokslm.pojo.MessageCard;
import com.demo.demokslm.pojo.Swiper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageCardServiceTest {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessageCardService messageCardService;

    @Test
    void TestAddMessageCard(){
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
        messageCardService.addMessageCard(messageCard);
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
    void TestDeleteMessageCardById(){
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
        messageCardService.deleteMessageCardById(999);
        assert(messageDao.selectById(999)==null);
    }

    @Test
    void TestUpdateMessageCard(){
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

        messageCardService.updateMessageCard(messageCard);

        MessageCard messageCard2 = messageDao.selectById(999);
        assert (messageCard2.getSendTime().equals("2022/11/11"));
        assert (messageCard2.getImageId()==3);


        messageDao.deleteById(999);
        assert(messageDao.selectById(999)==null);
    }

    @Test
    void TestFindMessageCardIdList(){
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

        List<Integer> list = messageCardService.findMessageCardIdList(999);
        assert (list.get(0) == 999);
        assert (list.get(1) == 1000);
        assert (list.size()==2);

        messageDao.deleteById(999);
        messageDao.deleteById(1000);
        messageDao.deleteById(1001);
        assert(messageDao.selectById(999)==null);
        assert(messageDao.selectById(1000)==null);
        assert(messageDao.selectById(1001)==null);
    }

    @Test
    void TestFindMessageCardById(){
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
        MessageCard messageCard1 = messageCardService.findMessageCardById(999);

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
}
