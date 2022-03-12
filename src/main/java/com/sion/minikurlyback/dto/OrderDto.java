package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto { // 주문페이지에서 받아오는 데이터
    // 상품리스트, 주소정보
    private List<OrderItemDto> orderItemList;
    private String addressBasic;
    private String addressDetail;
    private String zipCode;
    private Boolean mainAddress;

    // 아래 두개 삭제
    private Item item;
    private Integer count;

}
