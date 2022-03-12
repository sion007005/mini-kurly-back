package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.*;
import com.sion.minikurlyback.entity.*;
import com.sion.minikurlyback.repository.ItemRepository;
import com.sion.minikurlyback.repository.MemberRepository;
import com.sion.minikurlyback.repository.OrderItemRepository;
import com.sion.minikurlyback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    public OrderDetailDto getOrderPage(CartOrderDto cartOrderDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);
        Address mainAddress = addressService.findMainAddressByMember(member.getIdx());
        OrderDetailDto orderDetailDto = new OrderDetailDto();

        if (Objects.nonNull(mainAddress)) {
            orderDetailDto.setAddressBasic(mainAddress.getAddressDetail().getAddressBasic());
            orderDetailDto.setAddressDetail(mainAddress.getAddressDetail().getAddressDetail());
        }

        List<CartItemDto> cartItemDtoList = cartOrderDto.getCartItemList();
        List<OrderItemDetailDto> orderItemDetailDtos = getOrderItemList(cartItemDtoList);
        orderDetailDto.setOrderItemList(orderItemDetailDtos);
        return orderDetailDto;
    }

    private List<OrderItemDetailDto> getOrderItemList(List<CartItemDto> cartItemDtoList) {
        List<OrderItemDetailDto> orderItemDetailDtos = new ArrayList<>();

        for (CartItemDto cartItemDto : cartItemDtoList) {
            Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
            OrderItemDetailDto orderItemDetailDto = OrderItemDetailDto.builder()
                    .name(item.getName())
                    .brand(item.getBrand())
                    .orderPrice(item.getSalePrice())
                    .count(cartItemDto.getCount())
                    .imagePath(item.getImagePath())
                    .build();

            orderItemDetailDtos.add(orderItemDetailDto);
        }

        return orderItemDetailDtos;
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

    /**
     * 주문취소
     */
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        //주문 취소
        order.cancel();
    }

    /**
     * 주문아이디로 한 건의 주문정보 가져오기
     */
    public OrderDetailDto findById(Long orderId) {
        // TODO 현재 주문(Order)에는 배송정보가 없다
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(orderId);
        List<OrderItemDetailDto> orderItemDetailDtos = new ArrayList<>();

        for (OrderItem orderItem : orderItemList) {
            OrderItemDetailDto orderItemDetailDto = OrderItemDetailDto.builder()
                    .name(orderItem.getItem().getName())
                    .brand(orderItem.getItem().getBrand())
                    .orderPrice(orderItem.getOrderPrice())
                    .count(orderItem.getCount())
                    .imagePath(orderItem.getItem().getImagePath())
                    .build();

            orderItemDetailDtos.add(orderItemDetailDto);
        }
        OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                .orderStatus(order.getOrderStatus())
//                .addressBasic()
//                .addressDetail()
                .orderItemList(orderItemDetailDtos)
                .build();

        return orderDetailDto;
    }

    /**
     * TODO 로그인한 사용자의 주문 리스트 가져오기
     */
//    public List<OrderDto> findAll(String memberId) {
//
//    }

}
