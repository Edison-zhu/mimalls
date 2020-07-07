package com.zhuyanbing.mimall.Eo;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class CartAddEo {
    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
