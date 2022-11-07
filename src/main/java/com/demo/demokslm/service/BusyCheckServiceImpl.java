package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusyCheckServiceImpl implements BusyCheckService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public Map<String,Integer> checkBusyStatement() {
        Long res = seatDao.selectCount(new QueryWrapper<Seat>()
                .like("seatOccupancy", 1)
        );
        long all = seatDao.selectCount(new QueryWrapper<Seat>());
        double result = ((double)res/(double)all * 100);
        Map<String,Integer> busy = new HashMap<>();
        busy.put("busyStatment",(int)result);
        return busy;
    }
}
