package com.sion.minikurlyback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderItemDetailDto {
    private Long cartItemId;
    private String name;
    private String brand;
    private Integer salePrice;
    private Integer count;
    private String imagePath;
}
