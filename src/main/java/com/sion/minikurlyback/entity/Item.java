package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

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

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Category category;

    @Enumerated
    private SaleStatus saleStatus;
}
