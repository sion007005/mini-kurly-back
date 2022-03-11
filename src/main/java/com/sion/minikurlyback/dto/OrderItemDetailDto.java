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
    private String name;
    private String brand;
    private Integer orderPrice;
    private Integer count;
    private String imagePath;
}
