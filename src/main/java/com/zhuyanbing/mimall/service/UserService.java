package com.zhuyanbing.mimall.service;

import com.zhuyanbing.mimall.pojo.User;
import com.zhuyanbing.mimall.vo.ResponseVo;

public interface UserService {
    ResponseVo<User> register(User user);
    ResponseVo<User> login(String username,String password);
}
