package com.sion.minikurlyback.entity;

import com.sion.minikurlyback.enums.SaleStatus;
import com.sion.minikurlyback.exception.NotEnoughStockException;
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
public class Item extends BaseTimeEntity {
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

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int rest = this.stock - quantity;
        if (rest < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }

        this.stock = rest;
    }
}
