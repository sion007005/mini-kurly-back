package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.entity.Item;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    @NotNull
    private String name;
    private String brand;
    private String description;
    @PositiveOrZero
    private Integer originalPrice;
    @PositiveOrZero
    @NotNull(message = "판매가는 반드시 존재해야합니다.")
    private Integer salePrice;
    @PositiveOrZero(message = "재고수량은 마이너스가 될 수 없습니다.")
    private Integer stock;
    private String imagePath;
    private Boolean kurlyOnly;

    public static ItemDto from(Item item) {
        if(item == null) return null;

        return ItemDto.builder()
                .name(item.getName())
                .brand(item.getBrand())
                .description(item.getDescription())
                .originalPrice(item.getOriginalPrice())
                .salePrice(item.getSalePrice())
                .stock(item.getStock())
                .kurlyOnly(item.getKurlyOnly())
                .imagePath(item.getImagePath())
                .build();
    }
}
