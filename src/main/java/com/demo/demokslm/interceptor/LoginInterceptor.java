package com.demo.demokslm.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.demokslm.dao.TokenDao;
import com.demo.demokslm.pojo.Token;
import com.demo.demokslm.service.LoginServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Date;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description: 用户登录拦截器
 * @date 2022/10/13 22:30
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenDao tokenDao;

    //有default感觉不用配置after什么的
    private String secret = "Zpu3wJwXV4VQWrHcXV3ggzmjao8ni1QejZ6LJImJV/k=";

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,@Nullable Exception arg3)
            throws Exception {}

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, @Nullable ModelAndView modelAndView)
            throws Exception {}

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2)
            throws Exception {
        if(arg0.getRequestURI().contains("/login") || arg0.getRequestURI().contains("/register") || arg0.getRequestURI().contains("/error")){
            return true;
        }
        arg1.setContentType("text/html;charset=utf-8");
        ServletOutputStream resultWriter = arg1.getOutputStream();
        final String headerToken = arg0.getHeader("token");
        System.out.println(headerToken);
        if (null == headerToken || headerToken.trim().equals("")){
            resultWriter.write("你没有token，请登录".getBytes(StandardCharsets.UTF_8));
            resultWriter.flush();
            resultWriter.close();
            return false;
        }

        try {
            //这里有问题 io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(headerToken).getBody();
            System.out.println(claims);
            String tokenUserId = (String) claims.get("userId");
            int iTokenUserId = Integer.parseInt(tokenUserId);

            Token mytoken = (Token) tokenDao.selectOne(new QueryWrapper<Token>()
                    .like("userId",iTokenUserId)
            );

            if(null == mytoken){
                resultWriter.write("未发现您的token，请重新登陆".getBytes(StandardCharsets.UTF_8));
                resultWriter.flush();
                resultWriter.close();
                return false;
            }

            if (!headerToken.equals(mytoken.getToken())){
                resultWriter.print("您的token可能被修改过，请重新登陆");
                resultWriter.flush();
                resultWriter.close();
                return false;

            }

            Date tokenDate = claims.getExpiration();
            int overTime = (int)(new Date().getTime() - tokenDate.getTime())/1000;
            if(overTime > 60*60*24*3){
                resultWriter.write("您的登录授权已过期，请重新登录".getBytes(StandardCharsets.UTF_8));
                resultWriter.flush();
                resultWriter.close();
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
            resultWriter.write("总之token不正确，请重新登陆".getBytes(StandardCharsets.UTF_8));
            resultWriter.flush();
            resultWriter.close();
            return false;
        }
        return true;
    }
}
