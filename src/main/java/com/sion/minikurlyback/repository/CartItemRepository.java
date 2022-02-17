package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.dto.CartItemDetailDto;
import com.sion.minikurlyback.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.sion.minikurlyback.dto.CartItemDetailDto(ci.id, i.name, i.brand, i.salePrice, ci.count, i.imagePath) " +
            "from CartItem ci " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "order by ci.createdAt desc")
    List<CartItemDetailDto> findCartDetailDtoList(Long cartId);
}
