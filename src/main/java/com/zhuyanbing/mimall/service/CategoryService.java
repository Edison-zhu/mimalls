package com.zhuyanbing.mimall.service;

import com.zhuyanbing.mimall.vo.CategoryVo;
import com.zhuyanbing.mimall.vo.ResponseVo;

import java.util.List;

public interface CategoryService {
    ResponseVo<List<CategoryVo>> selectAll();
}
