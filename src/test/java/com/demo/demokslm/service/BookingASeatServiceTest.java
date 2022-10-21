package com.demo.demokslm.service;

import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
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
}
