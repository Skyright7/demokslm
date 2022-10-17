package com.demo.demokslm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.TokenDao;
import com.demo.demokslm.dao.UserDao;
import com.demo.demokslm.pojo.LoginForm;
import com.demo.demokslm.pojo.Token;
import com.demo.demokslm.pojo.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


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

    //public static Key keys =  Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //public static String secretString = Encoders.BASE64.encode(keys.getEncoded());

    private String secret = "Zpu3wJwXV4VQWrHcXV3ggzmjao8ni1QejZ6LJImJV/k=";

    @Override
    public String loginToGetToken(LoginForm loginForm) {
        //第一步：判断用户信息是否正确
        //第二步：如果第一次登录，就数据库写入一个新token并返回；如果不是更新数据库中的token对象
        //第三步：返回token信息给客户端

        //是否存在此用户
        String loginFormUserCaseId = loginForm.getUserCaseId();
        User user = userDao.selectOne(new QueryWrapper<User>()
                .like("userCaseId",loginFormUserCaseId));
        if(null == user){
            return "此用户不存在";
        }
        //检查密码是否正确
        String loginFormUserPassword = loginForm.getUserPassword();
        if (!user.getUserPassword().equals(loginFormUserPassword)){
            return "密码不正确";
        } //到此为止是对的
        //通过验证后判断是否是第一次登录
        int userId = user.getUserId();
        Token userToken = tokenDao.selectOne(new QueryWrapper<Token>()
                .like("userId",userId));
        if (null == userToken){
            //第一次登陆，数据库中新建一个token
            Token newToken = new Token();
            Date createDate = new Date();
            int nowTime = (int) (createDate.getTime()/1000);
            String tokenContent = tokenGenerator(userId,createDate);
            newToken.setUserId(userId);
            newToken.setToken(tokenContent);
            newToken.setBuildTime(nowTime);
            tokenDao.insert(newToken);
            return tokenContent;
        }
        else{
            //更新数据库的token对象
            Token newToken = new Token();
            Date updateDate = new Date();
            int nowTime = (int) (updateDate.getTime()/1000);
            String tokenContent = tokenGenerator(userId,updateDate);
            newToken.setUserId(userId);
            newToken.setToken(tokenContent);
            newToken.setBuildTime(nowTime);
            newToken.setId(userToken.getId());
            tokenDao.updateById(newToken);
            return tokenContent;
        }
    }

    private String tokenGenerator(int userId, Date date){
        JwtBuilder jwtBuilder = Jwts.builder().setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256").setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000*60*60))
                .claim("userId",String.valueOf(userId))
                .setIssuer("Rain")    // 设置签发人
                .signWith(SignatureAlgorithm.HS256,secret); //签名
        String jwtoken = jwtBuilder.compact();
        return jwtoken;
    }
}
