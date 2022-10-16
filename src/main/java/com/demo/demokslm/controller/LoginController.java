package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.LoginForm;
import com.demo.demokslm.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/15 21:41
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public Object login(@RequestBody LoginForm loginForm){
        return loginService.loginToGetToken(loginForm);
    }
}
