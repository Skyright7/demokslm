package com.demo.demokslm.service;

import com.demo.demokslm.dao.SeatDao;
import com.demo.demokslm.pojo.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 0:36
 */

@SpringBootTest
public class SeatServiceTest {
    @Autowired SeatService seatService;
    @Autowired SeatDao seatDao;

    @Test
    void TestFindSeatById() {
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatService.addSeat(seat);
        Seat seat1 = seatService.findSeatById(999);
        assert(seat1.getSeatId()==999);
        assert(seat1.getSeatOccupancy()==4);
        assert(seat1.getSeatPosition().equals("f3-d6"));
        assert(seat1.getSeatType()==9);
        assert(seat1.getSeatAvailableTime().equals("9-23"));
        seatDao.deleteById(999);
    }
    @Test
    void TestAddSeat() {
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatService.addSeat(seat);
        assert(seatDao.selectById(999).getSeatId() == 999);
        seatDao.deleteById(999);
    }
    @Test
    void TestUpdateSeatSeatById() {
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatService.addSeat(seat);
        assert(seatDao.selectById(999).getSeatOccupancy() == 4);
        assert(seatDao.selectById(999).getSeatType() == 9);
        seat.setSeatOccupancy(5);
        seat.setSeatType(10);
        seatService.updateSeat(seat);
        assert(seatDao.selectById(999).getSeatOccupancy() == 5);
        assert(seatDao.selectById(999).getSeatType() == 10);
        seatDao.deleteById(999);
    }
    @Test
    void TestDeleteSeatById() {
        Seat seat = new Seat();
        seat.setSeatId(999);
        seat.setSeatOccupancy(4);
        seat.setSeatPosition("f3-d6");
        seat.setSeatType(9);
        seat.setSeatAvailableTime("9-23");
        seatService.addSeat(seat);
        assert(seatDao.selectById(999).getSeatOccupancy() == 4);
        assert(seatDao.selectById(999).getSeatType() == 9);
        seatService.deleteSeatById(999);
        assert(seatDao.selectById(999) == null);
    }
}
