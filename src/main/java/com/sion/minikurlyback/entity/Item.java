package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String description;
    private Integer salePrice;
    private Integer originalPrice;
    private Integer stock;
    private boolean isKurlyOnly;
    private String imagePath;
//    private MultipartFile imageFile;

    @Enumerated
    private SaleStatus saleStatus;

}
