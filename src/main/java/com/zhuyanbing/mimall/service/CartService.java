package com.zhuyanbing.mimall.service;

import com.zhuyanbing.mimall.Eo.CartAddEo;
import com.zhuyanbing.mimall.pojo.Cart;
import com.zhuyanbing.mimall.vo.CartVo;
import com.zhuyanbing.mimall.vo.ResponseVo;

public interface CartService {
    ResponseVo<CartVo> add(Integer uid,CartAddEo cartAddEo);
}
