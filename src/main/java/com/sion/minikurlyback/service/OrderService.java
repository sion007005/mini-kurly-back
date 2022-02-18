package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.entity.Item;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.entity.Order;
import com.sion.minikurlyback.entity.OrderItem;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.repository.OrderItemRepository;
import com.sion.minikurlyback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;

    public Long order(List<OrderItemDto> orderItemDtoList, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        Order order = Order.createOrder(member);
        Order savedOrder = orderRepository.save(order);

        for (OrderItemDto orderItemDto : orderItemDtoList) {
            Item item = orderItemDto.getItem();
            OrderItem orderItem = OrderItem.createOrderItem(savedOrder, item, item.getSalePrice(), orderItemDto.getCount());
            orderItemRepository.save(orderItem);
        }

        return savedOrder.getId();
    }

}
