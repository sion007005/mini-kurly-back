package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByMemberIdx(Long memberIdx);
}
