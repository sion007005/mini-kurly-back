package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDto {
    private String name;
    private String brand;
    private String description;
    private Integer originalPrice;
    private Integer salePrice;
    private Integer stock;
    private String imageDownloadUrl;
    private boolean isKurlyOnly;

    public static ItemDto from(Item item) {
        if(item == null) return null;

        return ItemDto.builder()
                .name(item.getName())
                .brand(item.getBrand())
                .description(item.getDescription())
                .originalPrice(item.getOriginalPrice())
                .salePrice(item.getSalePrice())
                .stock(item.getStock())
                .isKurlyOnly(item.isKurlyOnly())
                .imageDownloadUrl(item.getImagePath())
                .build();
    }
}
