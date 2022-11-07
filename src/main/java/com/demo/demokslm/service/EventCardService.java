package com.demo.demokslm.service;

import com.demo.demokslm.pojo.EventCard;

import java.util.List;

public interface EventCardService {
    //增
    void addEventCard(EventCard eventCard);
    //删
    void deleteEventCardById(Integer id);
    //改
    void updateEventCard(EventCard eventCard);
    //查所有的event列表
    List<EventCard> findEventCardList();
    //查
    EventCard findEventCardById(Integer id);
}
