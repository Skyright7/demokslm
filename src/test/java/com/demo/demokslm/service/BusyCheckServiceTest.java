package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/20 23:55
 */
@SpringBootTest
public class BusyCheckServiceTest {

    @Autowired BusyCheckService busyCheckService;

    @Test
    void contextLoads() {
        int res =busyCheckService.checkBusyStatement();
        assert(res==80);
    }
}
