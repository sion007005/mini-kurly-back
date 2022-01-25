package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // 상품명은 중복되지 않도록 한다.
    private String name;

    private String brand;
    private String description;

    @Min(0)
    private Integer salePrice;
    @Min(0)
    private Integer originalPrice;
    @Min(0)
    private Integer stock;

    private boolean isKurlyOnly;
    private String imagePath;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated
    private SaleStatus saleStatus;
}
