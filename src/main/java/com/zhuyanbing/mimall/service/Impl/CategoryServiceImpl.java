package com.zhuyanbing.mimall.service.Impl;

import com.zhuyanbing.mimall.dao.CategoryMapper;
import com.zhuyanbing.mimall.pojo.Category;
import com.zhuyanbing.mimall.service.CategoryService;
import com.zhuyanbing.mimall.vo.CategoryVo;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List categoryVoList = new ArrayList();
        for (Category category : categories) {
            if (category.getParentId().equals(0)) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            }
        }
        findSubCategory(categoryVoList,categories);
        return ResponseVo.success(categoryVoList);
    }
        //lamdom表达式
//        List<CategoryVo> categoryVoList =categories.stream().filter(e->e.getParentId().equals(0))
//                .map(e->categoryVo2(e)).collect(Collectors.toList());
//        findSubCategory(categoryVoList,categories);
//        return ResponseVo.success(categoryVoList);
//    }
    public void findSubCategory(List<CategoryVo> categoryVoList,List<Category> categories){
        CategoryVo categoryVo = new CategoryVo();
        for (CategoryVo categoryVo1 : categoryVoList) {
            for (Category category : categories) {
                //如果相等设置子目录
                List<CategoryVo> categoryVoList1 = new ArrayList<>();
                if (categoryVo.getId().equals(category.getParentId())){
                    CategoryVo categoryVos = new CategoryVo();
                    BeanUtils.copyProperties(category, categoryVo);
                    categoryVoList1.add(categoryVo);
                }
                categoryVo.setSubCategories(categoryVoList1);
            }
        }
    }
//    private CategoryVo categoryVo2(Category category){
//        CategoryVo categoryVo = new CategoryVo();
//        BeanUtils.copyProperties(category,categoryVo);
//        return categoryVo;
//    }

}
