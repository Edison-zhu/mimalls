package com.zhuyanbing.mimall.vo;

import com.zhuyanbing.mimall.enums.RepEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class ResponseVo<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVo(T data) {
        this.data = data;
    }

    public ResponseVo(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseVo(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T>ResponseVo<T> success(T data){
        return new ResponseVo<T>(RepEnum.SUCCESS.getCode(),RepEnum.SUCCESS.getDesc(),data);
    }
    public static <T>ResponseVo<T> success(){
        return new ResponseVo<T>(RepEnum.SUCCESS.getCode(),RepEnum.SUCCESS.getDesc());
    }
    public static <T>ResponseVo<T> error(RepEnum repEnum){
        return new ResponseVo<T>(repEnum.getCode(),repEnum.getDesc());
    }
    public static <T>ResponseVo<T> error(RepEnum repEnum,String msg){
        return new ResponseVo<T>(repEnum.getCode(),msg);
    }
    public static <T>ResponseVo<T> error(RepEnum repEnum, BindingResult bindingResult){
        return new ResponseVo<T>(repEnum.getCode(),bindingResult.getFieldError().getField()+""+bindingResult.getFieldError().getDefaultMessage());
    }
}
