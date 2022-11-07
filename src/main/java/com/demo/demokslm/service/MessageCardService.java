package com.demo.demokslm.service;

import com.demo.demokslm.pojo.MessageCard;

import java.util.List;

public interface MessageCardService {
    //增
    void addMessageCard(MessageCard messageCard);
    //删
    void deleteMessageCardById(Integer id);
    //改
    void updateMessageCard(MessageCard messageCard);
    //查本用户的所有message
    List<MessageCard> findMessageCardList(Integer userId);
    //查
    MessageCard findMessageCardById(Integer id);
}
