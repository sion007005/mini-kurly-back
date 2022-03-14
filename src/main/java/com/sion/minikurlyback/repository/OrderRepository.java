package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberIdx(Long memberId);
}
