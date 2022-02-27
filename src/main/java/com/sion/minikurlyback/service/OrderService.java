package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.OrderDto;
import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.entity.*;
import com.sion.minikurlyback.repository.ItemRepository;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.repository.OrderItemRepository;
import com.sion.minikurlyback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final AddressService addressService;
    private final CartService cartService;

    public OrderDto getOrderPage(OrderDto orderDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);
        Address mainAddress = addressService.findMainAddressByMember(member.getIdx());

        if (Objects.nonNull(mainAddress)) {
            orderDto.setAddressBasic(mainAddress.getAddressBasic());
            orderDto.setAddressDetail(mainAddress.getAddressDetail());
            orderDto.setMainAddress(mainAddress.getMainAddress());
        }

        return orderDto;
    }

    public Long order(OrderDto orderDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        Order order = Order.createOrder(member);
        Order savedOrder = orderRepository.save(order);

        for (OrderItemDto orderItemDto : orderDto.getOrderItemList()) {
            Item item = itemRepository.findById(orderItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.createOrderItem(savedOrder, item, item.getSalePrice(), orderItemDto.getCount());
            orderItemRepository.save(orderItem);

            // 장바구니에서 삭제
            cartService.deleteCartItem(member.getIdx(), item.getId());
        }

        return savedOrder.getId();
    }


}
