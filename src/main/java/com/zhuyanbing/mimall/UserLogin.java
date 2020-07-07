package com.zhuyanbing.mimall;

import com.zhuyanbing.mimall.Exception.UserLoginException;
import com.zhuyanbing.mimall.enums.RepEnum;
import com.zhuyanbing.mimall.pojo.User;
import com.zhuyanbing.mimall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class UserLogin implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器开始执行");
        User user = (User)request.getSession().getAttribute("currentUser");
        if (user == null){
            throw new UserLoginException();
            //return false;
        }
        return true;
    }
}
