package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String description;
    private int salePrice;
    private int originalPrice;
    private int discountRate;
    private int stock;
    private boolean isKurlyOnly;
    private String imageFilePath;
//    private MultipartFile imageFile;

    @Enumerated
    private SaleStatus saleStatus;

}
