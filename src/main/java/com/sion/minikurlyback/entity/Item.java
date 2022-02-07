package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(unique = true, nullable = false) // 상품명은 중복되지 않도록 한다.
    private String name;

    private String brand;
    private String description;

    @Column(nullable = false)
    private Integer salePrice;

    private Integer originalPrice;

    @Column(columnDefinition = "integer default 0")
    private Integer stock;

    @Column(name="is_kurly_only", columnDefinition = "boolean default false")
    private Boolean kurlyOnly;
    private String imagePath;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;
}
