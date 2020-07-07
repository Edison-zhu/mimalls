package com.zhuyanbing.mimall.service.Impl;

import com.zhuyanbing.mimall.dao.UserMapper;
import com.zhuyanbing.mimall.enums.RepEnum;
import com.zhuyanbing.mimall.enums.RoleEnum;
import com.zhuyanbing.mimall.pojo.User;
import com.zhuyanbing.mimall.service.UserService;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo<User> register(User user) {
        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername>0){
            return ResponseVo.error(RepEnum.USERNAME_EXIST);
        }
        //error();
        user.setRole(RoleEnum.CUSTOMER.getCode());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        int result = userMapper.insertSelective(user);
        if (result==0){
            return ResponseVo.error(RepEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null){
            return ResponseVo.error(RepEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            return ResponseVo.error(RepEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return ResponseVo.success(user);
    }
    private void error(){
        throw new RuntimeException("意外错误");
    }
}
