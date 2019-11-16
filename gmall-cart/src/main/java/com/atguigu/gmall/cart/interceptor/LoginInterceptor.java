package com.atguigu.gmall.cart.interceptor;

import com.atguigu.core.bean.UserInfo;
import com.atguigu.core.utils.CookieUtils;
import com.atguigu.core.utils.JwtUtils;
import com.atguigu.gmall.cart.config.JwtProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;


/**
 * @author feilong
 * @create 2019-11-14 10:45
 */
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //threadLocal中的载荷信息：userId userKey
        UserInfo userInfo = new UserInfo();

        //获取cookie信息（GMALL-TOKEN ， UserKey）
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        String userKey = CookieUtils.getCookieValue(request, this.jwtProperties.getUserKeyName());
        if (StringUtils.isEmpty(userKey)){
            String uuid = UUID.randomUUID().toString();
            CookieUtils.setCookie(request , response , this.jwtProperties.getUserKeyName() , uuid , this.jwtProperties.getExpire());
        }
        userInfo.setUserKey(userKey);

        if(StringUtils.isEmpty(token)){
            THREAD_LOCAL.set(userInfo);
            return true;
        }

        try {
            //解析gmall_token
            Map<String, Object> userInfoMap = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

            userInfo.setUserId(Long.valueOf(userInfoMap.get("id").toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        THREAD_LOCAL.set(userInfo);
        return true;
    }
    public static UserInfo get(){

        return THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //因为咋们使用的是tomcat线程池，请求结束不代表线程结束
        THREAD_LOCAL.remove();
    }
}

