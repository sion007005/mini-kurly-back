package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
