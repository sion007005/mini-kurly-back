package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberIdx(Long memberIdx);
}
