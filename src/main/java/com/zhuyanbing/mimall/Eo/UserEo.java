package com.zhuyanbing.mimall.Eo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserEo {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
