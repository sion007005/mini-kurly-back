package com.sion.minikurlyback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDetailDto {
    private Long cartItemId;
    private String name;
    private String brand;
    private Integer salePrice;
    private Integer count;
    private String imagePath;

    public CartItemDetailDto(Long cartItemId, String name, String brand, Integer salePrice, Integer count, String imagePath) {
        this.cartItemId = cartItemId;
        this.name = name;
        this.brand = brand;
        this.salePrice = salePrice;
        this.count = count;
        this.imagePath = imagePath;
    }
}
