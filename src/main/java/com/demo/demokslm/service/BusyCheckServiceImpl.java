package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusyCheckServiceImpl implements BusyCheckService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public Integer checkBusyStatement() {
        Long res = seatDao.selectCount(new QueryWrapper<Seat>()
                .like("seatOccupancy", 1)
        );
        long all = seatDao.selectCount(new QueryWrapper<Seat>());
        double result = ((double)res/(double)all * 100);
        return (int)result;
    }
}
