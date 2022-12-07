package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.Order;
import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //get order by id
    @GetMapping(value = "/{id}")
    public ResponseResult<Order> findOrder(@PathVariable Integer id){
        Order order = orderService.findOrderById(id);
        return ResponseResult.success(order);
    }

    @PostMapping
    public ResponseResult<String> addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return ResponseResult.success("Successful add one order");
    }

    @PutMapping
    public ResponseResult<String> adjustOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        return ResponseResult.success("Successful adjust one order");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteOrderById(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return ResponseResult.success("Successful delete one order");
    }

    @GetMapping(value = "/{userId}/{itemId}")
    public ResponseResult<Order> findOrderByQrcode(@PathVariable Integer userId,@PathVariable Integer itemId){
        return ResponseResult.success(orderService.findOrderByQrcode(userId, itemId));
    }

    @GetMapping(value = "/list/{userId}")
    public ResponseResult<List<Integer>> findOrderIdList(@PathVariable Integer userId){
        List<Integer> result = orderService.findOrderIdList(userId);
        return ResponseResult.success(result);
    }
}
