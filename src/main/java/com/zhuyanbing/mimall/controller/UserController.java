package com.zhuyanbing.mimall.controller;

import com.zhuyanbing.mimall.Eo.UserEo;
import com.zhuyanbing.mimall.enums.RepEnum;
import com.zhuyanbing.mimall.pojo.User;
import com.zhuyanbing.mimall.service.Impl.UserServiceImpl;
import com.zhuyanbing.mimall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/relist")
    public ResponseVo register(@Valid @RequestBody UserEo userEo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.info("注册失败"+bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(RepEnum.NEED_LOGIN,bindingResult);
        }
        User user = new User();
        BeanUtils.copyProperties(userEo,user);
        userService.register(user);
        return ResponseVo.success(user);
    }
    @PostMapping("/login")
    public ResponseVo<User> login(@RequestBody User user, HttpServletRequest httpServletRequest){
        log.info("用户名={}",user.getUsername());
        ResponseVo<User> userResponseVo = userService.login(user.getUsername(),user.getPassword());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("currentUser",userResponseVo.getData());
        return userResponseVo;
    }
    @GetMapping("/userInfo")
    public ResponseVo userInfos(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("currentUser");
        return ResponseVo.success(user);
    }
    @PostMapping("/logout")
    public ResponseVo<User> logout(HttpSession httpSession){
        httpSession.removeAttribute("currentUser");

        return ResponseVo.success();
    }
}
