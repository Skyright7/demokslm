package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.Order;
import com.demo.demokslm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //get order by id
    @GetMapping(value = "/{id}")
    public Order findOrder(@PathVariable Integer id){
        Order order = orderService.findOrderById(id);
        return order;
    }

    @PostMapping
    public String addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return "Success";
    }

    @PutMapping
    public String adjustOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        return "Success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteOrderById(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return "Success";
    }

    @GetMapping(value = "/{userId}/{itemId}")
    public Order findOrderByQrcode(@PathVariable Integer userId,@PathVariable Integer itemId){
        return orderService.findOrderByQrcode(userId, itemId);
    }
}
