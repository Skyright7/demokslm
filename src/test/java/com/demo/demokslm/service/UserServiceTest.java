package com.demo.demokslm.service;

import com.demo.demokslm.dao.UserDao;
import com.demo.demokslm.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 0:52
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserDao userDao;

    @Test
    void TestFindUserById(){
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userService.addUser(user);
        User user1 = userService.findUserById(999);
        assert(user1.getUserId() == 999);
        assert(user1.getUserCaseId().equals("gxl999"));
        assert(user1.getUserPassword().equals("lgy233"));
        assert(user1.getUserName().equals("RainOutSide"));
        assert(user1.getUserGender() == 3);
        assert(user1.getUserGraduationYear() == 2024);
        assert(user1.getUserAvatar() == 3);
        assert(user1.getUserStatus() == 3);
        userDao.deleteById(999);

    }
    @Test
    void TestAddUser(){
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userService.addUser(user);
        assert(userDao.selectById(999).getUserId() == 999);
        userDao.deleteById(999);
    }
    @Test
    void TestUpdateUser(){
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userService.addUser(user);
        assert(userDao.selectById(999).getUserCaseId().equals("gxl999"));
        assert(userDao.selectById(999).getUserName().equals("RainOutSide"));
        user.setUserCaseId("gxl999888");
        user.setUserName("RainingOutSide");
        userService.updateUser(user);
        assert(userDao.selectById(999).getUserCaseId().equals("gxl999888"));
        assert(userDao.selectById(999).getUserName().equals("RainingOutSide"));
        userDao.deleteById(999);

    }
    @Test
    void TestDeleteUserById(){
        User user = new User();
        user.setUserId(999);
        user.setUserCaseId("gxl999");
        user.setUserPassword("lgy233");
        user.setUserName("RainOutSide");
        user.setUserEmailAddress("gxl999@case.edu");
        user.setUserPrivilege(2);
        user.setUserPreferredName("cristina");
        user.setUserAddress("1234road");
        user.setUserPhoneNumber("123456789");
        user.setUserGender(3);
        user.setUserGraduationYear(2024);
        user.setUserMajor("CST");
        user.setUserAvatar(3);
        user.setUserTitle("my title");
        user.setUserStatus(3);
        userService.addUser(user);
        assert(userDao.selectById(999).getUserId() == 999);
        assert(userDao.selectById(999).getUserCaseId().equals("gxl999"));
        assert(userDao.selectById(999).getUserName().equals("RainOutSide"));
        userService.deleteUserById(999);
        assert(userDao.selectById(999) == null);

    }
}
