package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.EventCard;
import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.service.EventCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventCardController {
    @Autowired
    private EventCardService eventCardService;

    @GetMapping
    public ResponseResult<List<EventCard>> findEventCardList(){
        List<EventCard> result = eventCardService.findEventCardList();
        return ResponseResult.success(result);
    }

    @PostMapping
    public ResponseResult<String> addEventCard(@RequestBody EventCard eventCard){
        eventCardService.addEventCard(eventCard);
        return ResponseResult.success("Successful add one EventCard");
    }
    @PutMapping
    public ResponseResult<String> adjustEventCard(@RequestBody EventCard eventCard){
        eventCardService.updateEventCard(eventCard);
        return ResponseResult.success("Successful adjust one EventCard");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteEventCardById(@PathVariable Integer id){
        eventCardService.deleteEventCardById(id);
        return ResponseResult.success("Successful delete one EventCard");
    }
}
