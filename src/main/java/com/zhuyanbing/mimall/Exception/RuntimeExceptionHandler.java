package com.zhuyanbing.mimall.Exception;

import com.zhuyanbing.mimall.enums.RepEnum;
import com.zhuyanbing.mimall.vo.ResponseVo;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo handle(RuntimeException e){
        return ResponseVo.error(RepEnum.ERROR,e.getMessage());
    }
    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginHandle(){
        return ResponseVo.error(RepEnum.NEED_LOGIN);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo NotValidException(MethodArgumentNotValidException e){
        return ResponseVo.error(RepEnum.PARAM_ERROR,e.getBindingResult());
    }
}
