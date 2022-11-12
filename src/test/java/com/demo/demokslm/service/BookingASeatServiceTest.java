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
        //f4的数据仅做测试使用
        //测试数据1
        Seat seat01 = new Seat();
        seat01.setSeatId(999);
        seat01.setSeatType(1);
        seat01.setSeatAvailableTime("9-23");
        seat01.setSeatOccupancy(1);
        seat01.setSeatPosition("f4-d1");
        seatDao.insert(seat01);
        //测试数据2
        Seat seat02 = new Seat();
        seat02.setSeatId(1000);
        seat02.setSeatType(2);
        seat02.setSeatAvailableTime("9-23");
        seat02.setSeatOccupancy(1);
        seat02.setSeatPosition("f4-d2");
        seatDao.insert(seat02);
        //测试数据3
        Seat seat03 = new Seat();
        seat03.setSeatId(1001);
        seat03.setSeatType(1);
        seat03.setSeatAvailableTime("12-23");
        seat03.setSeatOccupancy(1);
        seat03.setSeatPosition("f4-d1");
        seatDao.insert(seat03);
        StepOneBookingForm stepOneBookingForm = new StepOneBookingForm();
        stepOneBookingForm.setPreferredArea("");
        stepOneBookingForm.setPreferredFloor("f4");
        stepOneBookingForm.setArrivingTime(11);
        List<Seat> seatList = bookingASeatService.StepOneResult(stepOneBookingForm);
        Seat seat = seatList.get(0);
        assert(seat.getSeatType() == 1);
        assert(seat.getSeatId() == 999);
        assert(seat.getSeatOccupancy() == 1);
        assert(seat.getSeatPosition().equals("f4-d1"));
        assert(seat.getSeatAvailableTime().equals("9-23"));
        Seat seat1 = seatList.get(1);
        assert(seat1.getSeatId() == 1000);

        //删除测试数据
        seatDao.deleteById(999);
        seatDao.deleteById(1000);
        seatDao.deleteById(1001);
    }

    @Test
    void TestStepTwoResult(){
        //测试数据
        Seat seat01 = new Seat();
        seat01.setSeatId(999);
        seat01.setSeatType(1);
        seat01.setSeatAvailableTime("9-23");
        seat01.setSeatOccupancy(1);
        seat01.setSeatPosition("f4-d1");
        seatDao.insert(seat01);

        Seat seat = bookingASeatService.StepTwoResult(999);
        assert(seat.getSeatType() == 1);
        assert(seat.getSeatId() == 999);
        assert(seat.getSeatOccupancy() == 1);
        assert(seat.getSeatPosition().equals("f4-d1"));
        assert(seat.getSeatAvailableTime().equals("9-23"));

        //删除测试数据
        seatDao.deleteById(999);
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
