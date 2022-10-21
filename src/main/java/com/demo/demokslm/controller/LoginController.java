package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.LoginForm;
import com.demo.demokslm.pojo.ResponseResult;
import com.demo.demokslm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/15 21:41
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseResult<String> login(@RequestBody LoginForm loginForm){
        return ResponseResult.success(loginService.loginToGetToken(loginForm));
    }
}
