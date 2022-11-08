package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.MessageDao;
import com.demo.demokslm.pojo.EventCard;
import com.demo.demokslm.pojo.MessageCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageCardServiceImpl implements MessageCardService{
    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessageCard(MessageCard messageCard) {
        messageDao.insert(messageCard);
    }

    @Override
    public void deleteMessageCardById(Integer id) {
        messageDao.deleteById(id);
    }

    @Override
    public void updateMessageCard(MessageCard messageCard) {
        messageDao.updateById(messageCard);
    }

    @Override
    public List<Integer> findMessageCardIdList(Integer userId) {
        List<MessageCard> dataList = messageDao.selectList(new QueryWrapper<MessageCard>()
                .like("receiverId",userId)
        );
        List<Integer> result = new ArrayList<>();
        for (MessageCard messageCard:dataList) {
            result.add(messageCard.getMessageId());
        }
        return result;
    }

    @Override
    public MessageCard findMessageCardById(Integer id) {
        return messageDao.selectById(id);
    }

}
