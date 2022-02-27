package com.sion.minikurlyback.service;

import com.sion.minikurlyback.entity.Address;
import com.sion.minikurlyback.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address findMainAddressByMember(Long memberIdx) {
        return addressRepository.findByMemberIdx(memberIdx);
    }
}
