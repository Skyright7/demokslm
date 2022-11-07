package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.MessageCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseMapper<MessageCard> {
}
