package com.demo.demokslm.service;

import com.demo.demokslm.pojo.LoginForm;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/15 22:06
 */
public interface LoginService {
    Object loginToGetToken(LoginForm loginForm);
}

