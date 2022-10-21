package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.pojo.StepOneBookingForm;
import com.demo.demokslm.pojo.StepThreeBookingForm;
import com.demo.demokslm.service.BookingASeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 3:48
 */
@RestController
@RequestMapping("/bookingASeat")
public class BookingASeatController {
    @Autowired
    private BookingASeatService bookingASeatService;

    @PostMapping("/stepOne")
    public ResponseResult<List<Seat>> StepOne(@RequestBody StepOneBookingForm stepOneBookingForm){
        return ResponseResult.success(bookingASeatService.StepOneResult(stepOneBookingForm));
    }

    @GetMapping(value = "/stepTwo/{id}")
    public ResponseResult<Seat> StepTwo(@PathVariable int id){
        return ResponseResult.success(bookingASeatService.StepTwoResult(id));
    }

    @PostMapping("/stepThree")
    public ResponseResult<Integer> StepThree(@RequestBody StepThreeBookingForm stepThreeBookingForm){
        return ResponseResult.success(bookingASeatService.StepThreeResult(stepThreeBookingForm));
    }
}
