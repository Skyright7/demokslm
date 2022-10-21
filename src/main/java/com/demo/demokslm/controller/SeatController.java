package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.pojo.Seat;
import com.demo.demokslm.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    //get seat by id
    @GetMapping(value = "/{id}")
    public ResponseResult<Seat> findSeat(@PathVariable Integer id){
        Seat seat = seatService.findSeatById(id);
        return ResponseResult.success(seat);
    }

    @PostMapping
    public ResponseResult<String> addSeat(@RequestBody Seat seat){
        seatService.addSeat(seat);
        return ResponseResult.success("Successful add one seat");
    }

    @PutMapping
    public ResponseResult<String> adjustSeat(@RequestBody Seat seat){
        seatService.updateSeat(seat);
        return ResponseResult.success("Successful adjust one seat");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteSeatById(@PathVariable Integer id){
        seatService.deleteSeatById(id);
        return ResponseResult.success("Successful delete one seat");
    }

}
