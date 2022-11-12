package com.demo.demokslm.service;

import com.demo.demokslm.dao.SwiperDao;
import com.demo.demokslm.pojo.Swiper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SwiperServiceTest {
    @Autowired
    private SwiperDao swiperDao;

    @Autowired
    private SwiperService swiperService;

    @Test
    void TestAddSwiper(){
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperService.addSwiper(swiper);
        Swiper swiper1 = swiperDao.selectById(999);
        assert(swiper1.getSwiperId()==999);
        assert(swiper1.getImageId()==9);
        //删除测试用数据
        swiperDao.deleteById(999);
    }

    @Test
    void TestDeleteSwiperById(){
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        assert(swiperDao.selectById(999)!=null);
        swiperService.deleteSwiperById(999);
        assert(swiperDao.selectById(999)==null);
    }

    @Test
    void TestUpdateSwiper(){
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        Swiper swiper1 = swiperDao.selectById(999);
        assert(swiper1.getImageId()==9);
        swiper.setImageId(10);
        swiperService.updateSwiper(swiper);
        Swiper swiper2 = swiperDao.selectById(999);
        assert(swiper2.getImageId()==10);

        swiperDao.deleteById(999);
    }

    @Test
    void TestFindSwiperList(){
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        List<Swiper> list = swiperService.findSwiperList();
        Swiper swiper1 = list.get(list.size()-1);
        assert(swiper1.getSwiperId()==999);
        assert(swiper1.getImageId()==9);

        swiperDao.deleteById(999);

    }

    @Test
    void TestFindSwiperById(){
        assert(swiperDao.selectById(999)==null);
        Swiper swiper = new Swiper();
        swiper.setSwiperId(999);
        swiper.setImageId(9);
        swiperDao.insert(swiper);
        Swiper swiper1 = swiperService.findSwiperById(999);
        assert(swiper1.getImageId()==9);
        assert(swiper1.getSwiperId()==999);

        swiperDao.deleteById(999);
    }
}
