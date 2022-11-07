package com.demo.demokslm.service;

import com.demo.demokslm.pojo.Swiper;

import java.util.List;

public interface SwiperService {
    //增
    void addSwiper(Swiper swiper);
    //删
    void deleteSwiperById(Integer id);
    //改
    void updateSwiper(Swiper swiper);
    //查全部
    List<Swiper> findSwiperList();
    //找一个
    Swiper findSwiperById(Integer id);
}
