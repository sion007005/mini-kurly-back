package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.OrderDto;
import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.entity.Item;
import com.sion.minikurlyback.entity.Member;
import com.sion.minikurlyback.entity.Order;
import com.sion.minikurlyback.entity.OrderItem;
import com.sion.minikurlyback.repository.ItemRepository;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.repository.OrderItemRepository;
import com.sion.minikurlyback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
    여러 상품을 한번에 주문 (장바구니)
     */
    public Long order(List<OrderDto> orderDtoList, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        Order order = Order.createOrder(member);
        Order savedOrder = orderRepository.save(order);

        for (OrderDto orderDto : orderDtoList) {
            Item item = orderDto.getItem();
            OrderItem orderItem = OrderItem.createOrderItem(savedOrder, item, item.getSalePrice(), orderDto.getCount());
            orderItemRepository.save(orderItem);
        }

        return savedOrder.getId();
    }

    /*
    상품 상세페이지에서 단독 상품을 주문
     */
    public Long orderOneItem(OrderItemDto orderItemDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);

        Order order = Order.createOrder(member);
        Order savedOrder = orderRepository.save(order);

        Item item = itemRepository.findById(orderItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        OrderItem orderItem = OrderItem.createOrderItem(savedOrder, item, item.getSalePrice(), orderItemDto.getCount());
        orderItemRepository.save(orderItem);

        return savedOrder.getId();
    }

}
