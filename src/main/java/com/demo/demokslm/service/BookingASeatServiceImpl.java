package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Order;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
import com.demo.demokslm.pojo.StepThreeBookingForm;
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

    @Autowired
    private OrderDao orderDao;

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

    @Override
    public Seat StepTwoResult(Integer id) {
        return seatDao.selectById(id);
    }

    @Override
    public Integer StepThreeResult(StepThreeBookingForm stepThreeBookingForm) {
        Seat seat = seatDao.selectById(stepThreeBookingForm.getSeatId());
        String avaTime = seat.getSeatAvailableTime();
        List<String> newTimeArr = new ArrayList<String>();
        String[] timeArrAll = avaTime.split(",");
        List<Integer> startTime = new ArrayList<Integer>();
        List<Integer> endTime = new ArrayList<Integer>();
        for(String timeArrAlls:timeArrAll){
            String s1 = timeArrAlls.split("-")[0];
            String s2 = timeArrAlls.split("-")[1];
            startTime.add(Integer.parseInt(s1));
            endTime.add(Integer.parseInt(s2));
            for (int i = 0; i < startTime.size(); i++){
                if (startTime.get(i)<=stepThreeBookingForm.getStartTime() | stepThreeBookingForm.getEndTime() <=endTime.get(i)){
                    if(stepThreeBookingForm.getEndTime() == endTime.get(i)){
                        if(stepThreeBookingForm.getStartTime() == startTime.get(i)){
                            //什么都不做跳过这段，等于直接删掉这段ava时间就好
                        }
                        else {
                            newTimeArr.add(startTime.get(i) + "-" + stepThreeBookingForm.getStartTime());
                        }
                    }
                    else {
                        if(stepThreeBookingForm.getStartTime() == startTime.get(i)){
                            newTimeArr.add(stepThreeBookingForm.getEndTime() + "-" + endTime.get(i));
                        }
                        else {
                            newTimeArr.add(startTime.get(i) + "-" + stepThreeBookingForm.getStartTime());
                            newTimeArr.add(stepThreeBookingForm.getEndTime() + "-" + endTime.get(i));
                        }

                    }
                }
                else{
                    newTimeArr.add(startTime.get(i) + "-" + endTime.get(i));
                }
            }
        }
        String resTime = "";
        for(String s2:newTimeArr){
            resTime = resTime + "," +s2;
        }
        resTime = resTime.substring(1);
        seat.setSeatAvailableTime(resTime);
        seatDao.updateById(seat);
        //新建订单
        Order order = new Order();
        order.setOrderTime(stepThreeBookingForm.getStartTime()+"-"+stepThreeBookingForm.getEndTime());
        order.setCustomId(stepThreeBookingForm.getUserId());
        order.setOrderStatus(1);
        order.setOrderItemId(stepThreeBookingForm.getSeatId());
        orderDao.insert(order);
        int id = order.getOrderId();
        return id;
    }
}
