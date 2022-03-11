package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
/**
 * 주문완료 후 주문내역을 확인하는 페이지용 dto
 */
public class OrderDetailDto {
    private Long orderId;
    private OrderStatus orderStatus;
//    private String addressBasic;
//    private String addressDetail;
    private List<OrderItemDetailDto> orderItemList;
}
