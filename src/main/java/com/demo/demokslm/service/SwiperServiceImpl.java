package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.SwiperDao;
import com.demo.demokslm.pojo.Swiper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService{
    @Autowired
    private SwiperDao swiperDao;

    @Override
    public void addSwiper(Swiper swiper) {
        swiperDao.insert(swiper);
    }

    @Override
    public void deleteSwiperById(Integer id) {
        swiperDao.deleteById(id);
    }

    @Override
    public void updateSwiper(Swiper swiper) {
        swiperDao.updateById(swiper);
    }

    @Override
    public List<Swiper> findSwiperList() {
        return swiperDao.selectList(new QueryWrapper<Swiper>());
    }

    @Override
    public Swiper findSwiperById(Integer id) {
        return swiperDao.selectById(id);
    }
}
