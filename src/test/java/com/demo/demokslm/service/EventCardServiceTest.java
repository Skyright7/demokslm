package com.demo.demokslm.service;

import com.demo.demokslm.dao.EventDao;
import com.demo.demokslm.pojo.EventCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventCardServiceTest {
    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventCardService eventCardService;

    @Test
    void TestAddEventCard(){
        assert(eventDao.selectById(999)==null);
        EventCard eventCard = new EventCard();
        eventCard.setEventId(999);
        eventCard.setHeader("233");
        eventCard.setDetail("2233");
        eventCard.setSnapshot("2");
        eventCard.setImageId(1);
        eventCard.setHoldTime("2020/02/02");
        eventCardService.addEventCard(eventCard);
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
    void TestDeleteEventCardById(){
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
        eventCardService.deleteEventCardById(999);
        assert(eventDao.selectById(999)==null);
    }

    @Test
    void TestUpdateEventCard(){
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
        eventCardService.updateEventCard(eventCard);
        EventCard eventCard2 = eventDao.selectById(999);
        assert(eventCard2.getEventId()==999);
        assert(eventCard2.getHoldTime().equals("2021/03/03"));
        assert(eventCard2.getImageId()==3);

        eventDao.deleteById(999);
    }

    @Test
    void TestFindEventCardList(){
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
        List<EventCard> list = eventCardService.findEventCardList();
        EventCard eventCard1 = list.get(list.size()-1);
        assert(eventCard1.getEventId()==999);
        assert (eventCard1.getDetail().equals("2233"));
        assert (eventCard1.getHeader().equals("233"));
        assert (eventCard1.getSnapshot().equals("2"));
        assert (eventCard1.getImageId()==1);
        assert (eventCard1.getHoldTime().equals("2020/02/02"));

        eventDao.deleteById(999);

    }

    @Test
    void TestFindEventCardById(){
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
        EventCard eventCard1 = eventCardService.findEventCardById(999);
        assert(eventCard1.getEventId()==999);
        assert (eventCard1.getDetail().equals("2233"));
        assert (eventCard1.getHeader().equals("233"));
        assert (eventCard1.getSnapshot().equals("2"));
        assert (eventCard1.getImageId()==1);
        assert (eventCard1.getHoldTime().equals("2020/02/02"));

        eventDao.deleteById(999);
    }

    @Test
    void TestFindEventIdList(){
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
        List<Integer> list = eventCardService.findEventIdList();
        assert (list.get(list.size()-1)==999);

        eventDao.deleteById(999);
    }
}
