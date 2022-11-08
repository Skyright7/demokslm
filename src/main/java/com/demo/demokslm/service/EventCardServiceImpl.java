package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.EventDao;
import com.demo.demokslm.pojo.EventCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Integer> findEventIdList() {
        LambdaQueryWrapper<EventCard> lambdaQueryWrapper =
                new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(EventCard::getEventId);
        return eventDao.selectObjs(lambdaQueryWrapper).stream()
                .map(o ->(Integer) o)
                .collect(Collectors.toList());
    }
}
