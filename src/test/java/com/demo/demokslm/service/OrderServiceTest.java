package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 0:05
 */
@SpringBootTest
public class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired OrderDao orderDao;

    @Test
    void TestFindOrderById() {
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderService.addOrder(order);
        Order orderRes = orderService.findOrderById(999);
        assert(orderRes.getOrderId() == 999);
        assert(orderRes.getOrderStatus() == 2);
        assert(orderRes.getCustomId() == 1);
        assert(orderRes.getOrderItemId() == 1);
        orderDao.deleteById(999);
    }

    @Test
    void TestAddOrder() {
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderService.addOrder(order);
        assert(orderDao.selectById(999).getOrderId() == 999);
        orderDao.deleteById(999);
    }

    @Test
    void TestUpdateOrder(){
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderService.addOrder(order);
        assert(orderDao.selectById(999).getCustomId() == 1);
        assert(orderDao.selectById(999).getOrderStatus() == 2);
        order.setOrderStatus(3);
        order.setCustomId(2);
        orderService.updateOrder(order);
        assert(orderDao.selectById(999).getCustomId() == 2);
        assert(orderDao.selectById(999).getOrderStatus() == 3);
        orderDao.deleteById(999);
    }

    @Test
    void TestDeleteOrder(){
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(2);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderService.addOrder(order);
        assert(orderDao.selectById(999).getCustomId() == 1);
        orderService.deleteOrderById(999);
        assert(orderDao.selectById(999) == null);
    }

    @Test
    void TestFindOrderByQrcode(){
        Order order = new Order();
        order.setOrderItemId(1);
        order.setOrderStatus(1);
        order.setOrderTime("9-11");
        order.setCustomId(1);
        order.setOrderId(999);
        orderService.addOrder(order);
        assert(orderDao.selectById(999).getCustomId() == 1);
        Order order1 = orderService.findOrderByQrcode(1,1);
        assert(order1.getOrderId() == 999);
        assert(order1.getOrderStatus() == 1);
        assert(order1.getCustomId() == 1);
        assert(order1.getOrderItemId() == 1);
        orderDao.deleteById(999);
    }
}
