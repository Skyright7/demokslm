package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.EventDao;
import com.demo.demokslm.pojo.EventCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCardServiceImpl implements EventCardService{
    @Autowired
    private EventDao eventDao;

    @Override
    public void addEventCard(EventCard eventCard) {
        eventDao.insert(eventCard);
    }

    @Override
    public void deleteEventCardById(Integer id) {
        eventDao.deleteById(id);
    }

    @Override
    public void updateEventCard(EventCard eventCard) {
        eventDao.updateById(eventCard);
    }

    @Override
    public List<EventCard> findEventCardList() {
        return eventDao.selectList(new QueryWrapper<EventCard>());
    }

    @Override
    public EventCard findEventCardById(Integer id) {
        return eventDao.selectById(id);
    }
}
