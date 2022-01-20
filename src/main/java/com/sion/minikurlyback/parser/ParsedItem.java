package com.sion.minikurlyback.parser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsedItem {
    private String name;
    private String description;
    private String brand;
    private Integer originalPrice;
    private Integer salePrice;
    private Integer discountRate;
    private Boolean isKurlyOnly;
    private String imageUrl;
}
