package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.CartItemDetailDto;
import com.sion.minikurlyback.dto.CartItemDto;
import com.sion.minikurlyback.dto.OrderItemDto;
import com.sion.minikurlyback.entity.*;
import com.sion.minikurlyback.repository.CartItemRepository;
import com.sion.minikurlyback.repository.CartRepository;
import com.sion.minikurlyback.repository.ItemRepository;
import com.sion.minikurlyback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final OrderService orderService;

    /**
     * 장바구니에 상품추가
     */
    public Long addCart(CartItemDto cartItemDto, String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);
        Cart cart = cartRepository.findByMemberIdx(member.getIdx());
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        if (savedCartItem != null) { // 동일 상품이 추가되어 있다면 수량을 업데이트한다.
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        }

        CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
        cartItemRepository.save(cartItem);
        return cartItem.getId();
    }

    /**
     * 상품 수량 변경
     */
    public void updateItemCount(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count);
    }

    /**
     * 상품 삭제
     */
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    /**
     * 상품 목록보기
     * TODO controller에서 memberIdx로 받아오기(인증관련 로직부터 수정)
     */
    public List<CartItemDetailDto> getMyCartList(String memberId) {
        Member member = memberRepository.findOneByMemberId(memberId);
        Cart cart = cartRepository.findByMemberIdx(member.getIdx());

        List<CartItemDetailDto> cartList = new ArrayList<>();
        if (cart == null) {
            return cartList;
        }

        return cartItemRepository.findCartDetailDtoList(cart.getId());
    }


    /**
     * 장바구니에서 선택된 상품 주문하고, 장바구니에서 삭제하기
     */
    public Long orderCartItem(List<CartItemDto> cartItemList, String memberId) {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        for (CartItemDto cartItemDto : cartItemList) {
            Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setItem(item);
            orderItemDto.setCount(cartItemDto.getCount());

            orderItemDtoList.add(orderItemDto);
            deleteCartItem(cartItemDto.getCartItemId());
        }

        Long orderId = orderService.order(orderItemDtoList, memberId);

        return orderId;
    }
}
