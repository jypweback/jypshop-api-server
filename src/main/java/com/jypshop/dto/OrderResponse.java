package com.jypshop.dto;

import com.jypshop.domain.Order;
import com.jypshop.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private MemberResponse memberResponse;

    private DeliveryResponse deliveryResponse;

    private List<OrderItemResponse> orderItemResponseList = new ArrayList<>();

    @Builder
    public OrderResponse(Long orderId, LocalDateTime orderDate, OrderStatus orderStatus, MemberResponse memberResponse, DeliveryResponse deliveryResponse, List<OrderItemResponse> orderItemResponseList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.memberResponse = memberResponse;
        this.deliveryResponse = deliveryResponse;
        this.orderItemResponseList = orderItemResponseList;
    }
}
