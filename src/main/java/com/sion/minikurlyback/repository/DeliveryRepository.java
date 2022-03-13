package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
