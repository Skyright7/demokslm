package com.demo.demokslm.service;

import com.demo.demokslm.dao.OrderDao;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Order;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
import com.demo.demokslm.pojo.StepThreeBookingForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 2:11
 */
@SpringBootTest
public class BookingASeatServiceTest {
    @Autowired BookingASeatService bookingASeatService;

    @Autowired SeatDao seatDao;

    @Autowired OrderDao orderDao;

    @Test
    void TestStepOneResult(){
        StepOneBookingForm stepOneBookingForm = new StepOneBookingForm();
        stepOneBookingForm.setPreferredArea("d5");
        stepOneBookingForm.setPreferredFloor("f3");
        stepOneBookingForm.setArrivingTime(10);
        List<Seat> seatList = bookingASeatService.StepOneResult(stepOneBookingForm);
        Seat seat = seatList.get(0);
        assert(seat.getSeatType() == 1);
        assert(seat.getSeatId() == 1);
        assert(seat.getSeatOccupancy() == 1);
        assert(seat.getSeatPosition().equals("f3-d5"));
        assert(seat.getSeatAvailableTime().equals("9-23"));
        Seat seat1 = seatList.get(1);
        assert(seat1.getSeatId() == 6);
    }

    @Test
    void TestStepTwoResult(){
        Seat seat = bookingASeatService.StepTwoResult(1);
        assert(seat.getSeatType() == 1);
        assert(seat.getSeatOccupancy() == 1);
        assert(seat.getSeatId() == 1);
    }

    @Test
    void TestStepThreeResult(){
        //创建测试用seat数据
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatType(1);
        seat.setSeatAvailableTime("9-23");
        seat.setSeatOccupancy(1);
        seat.setSeatPosition("f3-d5");
        seatDao.insert(seat);
        assert(seatDao.selectById(999) != null);
        StepThreeBookingForm stepThreeBookingForm = new StepThreeBookingForm();
        stepThreeBookingForm.setSeatId(999);
        stepThreeBookingForm.setUserId(1);
        stepThreeBookingForm.setStartTime(11);
        stepThreeBookingForm.setEndTime(15);
        int orderId = bookingASeatService.StepThreeResult(stepThreeBookingForm);
        assert(seatDao.selectById(999).getSeatAvailableTime().equals("9-11,15-23"));
        assert(orderDao.selectById(orderId).getOrderItemId() == 999);
        assert(orderDao.selectById(orderId).getCustomId() == 1);
        assert(orderDao.selectById(orderId).getOrderTime().equals("11-15"));
        orderDao.deleteById(orderId);
        seatDao.deleteById(999);
    }
}
