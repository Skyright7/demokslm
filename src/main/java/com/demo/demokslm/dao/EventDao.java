package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.EventCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventDao extends BaseMapper<EventCard> {
}
