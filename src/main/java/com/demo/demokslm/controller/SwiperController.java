package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.pojo.Swiper;
import com.demo.demokslm.pojo.User;
import com.demo.demokslm.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swiper")
public class SwiperController {
    @Autowired
    private SwiperService swiperService;

    @GetMapping
    public ResponseResult<List<Swiper>> findSwiper(){
        List<Swiper> result = swiperService.findSwiperList();
        return ResponseResult.success(result);
    }
//    @GetMapping(value = "/{id}")
//    public ResponseResult<Swiper> findOneSwiper(@PathVariable Integer id){
//        Swiper swiper = swiperService.findSwiperById(id);
//        return ResponseResult.success(swiper);
//    }
    @PostMapping
    public ResponseResult<String> addSwiper(@RequestBody Swiper swiper){
        swiperService.addSwiper(swiper);
        return ResponseResult.success("Successful add one Swiper");
    }
    @PutMapping
    public ResponseResult<String> adjustSwiper(@RequestBody Swiper swiper){
        swiperService.updateSwiper(swiper);
        return ResponseResult.success("Successful adjust one Swiper");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteSwiperById(@PathVariable Integer id){
        swiperService.deleteSwiperById(id);
        return ResponseResult.success("Successful delete one Swiper");
    }
}
