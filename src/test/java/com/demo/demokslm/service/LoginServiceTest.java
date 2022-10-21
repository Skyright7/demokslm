package com.demo.demokslm.service;

import com.demo.demokslm.pojo.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/21 0:00
 */
@SpringBootTest
public class LoginServiceTest {

    @Autowired LoginService loginService;

    @Test
    void contextLoads() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUserCaseId("gxl405");
        loginForm.setUserPassword("123");
        String token = loginService.loginToGetToken(loginForm);
        System.out.println(token);
    }
}
