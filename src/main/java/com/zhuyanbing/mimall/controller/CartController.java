package com.zhuyanbing.mimall.controller;

import com.zhuyanbing.mimall.Eo.CartAddEo;
import com.zhuyanbing.mimall.vo.CartVo;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @PostMapping("/carts")
    public ResponseVo<CartVo> AddCart(@RequestBody CartAddEo cartAddEo){
        return null;
    }
}
