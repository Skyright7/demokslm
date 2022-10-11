package com.demo.demokslm.service;

import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService{

    @Autowired
    private SeatDao seatDao;

    @Override
    public Seat findSeatById(Integer id) {
        return seatDao.selectById(id);
    }

    @Override
    public void addSeat(Seat seat) {
        seatDao.insert(seat);
    }

    @Override
    public void updateSeat(Seat seat) {
        seatDao.updateById(seat);
    }

    @Override
    public void deleteSeatById(Integer id) {
        seatDao.deleteById(id);
    }
}
