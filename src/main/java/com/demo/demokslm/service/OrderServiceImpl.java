package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.pojo.EventCard;
import com.demo.demokslm.pojo.MessageCard;
import com.demo.demokslm.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order findOrderById(Integer id) {
        return orderDao.selectById(id);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateById(order);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order findOrderByQrcode(Integer customId, Integer userItemId) {
        return orderDao.selectOne(new QueryWrapper<Order>()
                .like("customId",customId)
                .like("orderItemId",userItemId)
                .like("orderStatus",1)
        );
    }

    @Override
    public List<Integer> findOrderIdList(Integer userId) {
        List<Order> dataList = orderDao.selectList(new QueryWrapper<Order>()
                .like("customId",userId)
        );
        List<Integer> result = new ArrayList<>();
        for (Order orders:dataList) {
            result.add(orders.getOrderId());
        }
        return result;
    }
}
