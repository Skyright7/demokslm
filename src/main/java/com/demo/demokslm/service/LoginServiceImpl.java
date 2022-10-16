package com.demo.demokslm.service;

import com.demo.demokslm.dao.TokenDao;
import com.demo.demokslm.dao.UserDao;
import com.demo.demokslm.pojo.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description:
 * @date 2022/10/15 22:06
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Object loginToGetToken(LoginForm loginForm) {
        //第一步：判断用户信息是否正确
        //第二步：如果第一次登录，就数据库写入一个新token并返回；如果不是更新数据库中的token对象
        //第三步：返回token信息给客户端
        return null;
    }
}
