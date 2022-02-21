package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategoryIdOrderByCreatedAtDesc(Long categoryId, Pageable pageable);
}
