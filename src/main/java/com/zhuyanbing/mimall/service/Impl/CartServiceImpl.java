package com.zhuyanbing.mimall.service.Impl;

import com.google.gson.Gson;
import com.zhuyanbing.mimall.Eo.CartAddEo;
import com.zhuyanbing.mimall.dao.ProductMapper;
import com.zhuyanbing.mimall.enums.RepEnum;
import com.zhuyanbing.mimall.pojo.Cart;
import com.zhuyanbing.mimall.pojo.Product;
import com.zhuyanbing.mimall.service.CartService;
import com.zhuyanbing.mimall.vo.CartVo;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CartServiceImpl implements CartService {
    private final static String CART_RESID_KEY = "cart_%d";
    private Gson gson = new Gson();
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public ResponseVo<CartVo> add(Integer uid,CartAddEo cartAddEo) {
        Product product = productMapper.selectByPrimaryKey(cartAddEo.getProductId());
        if (product == null){
            return ResponseVo.error(RepEnum.PRODUCT_NOT_EXIST);
        }
        if (!product.getStatus().equals(1)){
            return ResponseVo.error(RepEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        if (product.getStock()<=0){
            return ResponseVo.error(RepEnum.PROODUCT_STOCK_ERROR);
        }
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        String redisKey = String.format(CART_RESID_KEY,uid);
        String value = hashOperations.get(redisKey,String.valueOf(product.getId()));
        if (StringUtils.isEmpty(value)){

        }
        hashOperations.put(redisKey,String.valueOf(product.getId()),gson.toJson(new Cart(product.getId(),1,cartAddEo.getSelected())));
        return null;
    }
}
