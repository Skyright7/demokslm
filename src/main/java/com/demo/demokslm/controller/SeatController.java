package com.demo.demokslm.controller;

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
    public Seat findSeat(@PathVariable Integer id){
        Seat seat = seatService.findSeatById(id);
        return seat;
    }

    @PostMapping
    public String addSeat(@RequestBody Seat seat){
        seatService.addSeat(seat);
        return "Success";
    }

    @PutMapping
    public String adjustSeat(@RequestBody Seat seat){
        seatService.updateSeat(seat);
        return "Success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSeatById(@PathVariable Integer id){
        seatService.deleteSeatById(id);
        return "Success";
    }

}
