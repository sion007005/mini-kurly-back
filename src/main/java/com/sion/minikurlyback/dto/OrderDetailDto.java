package com.sion.minikurlyback.dto;

import com.sion.minikurlyback.enums.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 주문전/주문완료 후 주문 상세내역을 확인하는 페이지용 dto
 */
public class OrderDetailDto {
//    private Long orderId;
    private OrderStatus orderStatus;
    private String addressBasic;
    private String addressDetail;
    private String zipCode;
    private List<OrderItemDetailDto> orderItemList;
}
