package com.zhuyanbing.mimall.controller;

import com.zhuyanbing.mimall.service.Impl.CategoryServiceImpl;
import com.zhuyanbing.mimall.vo.CategoryVo;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CateController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @GetMapping("/category")
    public ResponseVo<List<CategoryVo>> selectAll(){
        return categoryService.selectAll();
    }
}
