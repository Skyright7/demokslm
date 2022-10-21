package com.demo.demokslm.service;

import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;

import java.util.List;
import java.util.Map;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 1:23
 */
public interface BookingASeatService {
    List<Seat> StepOneResult(StepOneBookingForm stepOneBookingForm);
}
