package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.Seat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatDao extends BaseMapper<Seat> {
}
