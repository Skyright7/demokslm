package com.demo.demokslm.service;


import com.demo.demokslm.pojo.Seat;

public interface SeatService {
    //查询
    Seat findSeatById(Integer id);
    //添加
    void addSeat(Seat seat);
    //修改
    void updateSeat(Seat seat);
    //删除
    void deleteSeatById(Integer id);
}
