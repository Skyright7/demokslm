package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
