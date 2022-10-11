package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
}
