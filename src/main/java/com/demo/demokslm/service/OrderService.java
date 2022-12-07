package com.demo.demokslm.service;


import com.demo.demokslm.pojo.Order;

import java.util.List;

public interface OrderService {
    //查询
    Order findOrderById(Integer id);
    //添加
    void addOrder(Order order);
    //修改
    void updateOrder(Order order);
    //删除
    void deleteOrderById(Integer id);

    //Qrcode 查询order
    Order findOrderByQrcode(Integer customId, Integer userItemId);

    List<Integer> findOrderIdList(Integer userId);
}
