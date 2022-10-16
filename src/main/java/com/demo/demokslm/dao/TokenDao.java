package com.demo.demokslm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.demokslm.pojo.Token;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description: 为token的数据访问方法
 * @date 2022/10/13 22:27
 */
@Mapper
public interface TokenDao extends BaseMapper<Token> {
}
