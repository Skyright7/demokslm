package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.service.BusyCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/busyCheck")
public class BusyCheckController {
    @Autowired
    private BusyCheckService busyCheckService;

    @GetMapping
    public ResponseResult<Map<String,Integer>> checkBusy(){
        return ResponseResult.success(busyCheckService.checkBusyStatement());
    }
}
