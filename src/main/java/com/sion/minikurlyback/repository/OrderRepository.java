package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByMemberIdx(String memberId);
}
