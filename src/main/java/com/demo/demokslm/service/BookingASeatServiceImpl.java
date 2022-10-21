package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 1:34
 */
@Service
public class BookingASeatServiceImpl implements BookingASeatService{

    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> StepOneResult(StepOneBookingForm stepOneBookingForm) {
        String seatPosition = stepOneBookingForm.getPreferredFloor() + "-" +stepOneBookingForm.getPreferredArea();
        List<Seat> list =  seatDao.selectList(new QueryWrapper<Seat>()
                .like("seatPosition",seatPosition)
                .like("seatOccupancy",1));
        List<Seat> avaList = new ArrayList<Seat>();
        int userArriveTime = stepOneBookingForm.getArrivingTime();
        for(Seat s:list){
            String avaTime = s.getSeatAvailableTime();
            String[] timeArrAll = avaTime.split(",");
            List<Integer> startTime = new ArrayList<Integer>();
            List<Integer> endTime = new ArrayList<Integer>();
            for(String timeArrAlls:timeArrAll){
                String s1 = timeArrAlls.split("-")[0];
                String s2 = timeArrAlls.split("-")[1];
                startTime.add(Integer.parseInt(s1));
                endTime.add(Integer.parseInt(s2));
                for (int i = 0; i < startTime.size(); i++) {
                    if(startTime.get(i)<=userArriveTime && userArriveTime<endTime.get(i)){
                        avaList.add(s);
                    }
                }
            }
        }
        return avaList;
    }
}
