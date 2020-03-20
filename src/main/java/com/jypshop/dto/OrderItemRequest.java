package com.jypshop.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/18
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class OrderItemRequest {

    private Long itemId;

    private int count;

    private int orderPrice;

    @Builder
    public OrderItemRequest(Long itemId, int count, int orderPrice) {
        this.itemId = itemId;
        this.count = count;
        this.orderPrice = orderPrice;
    }
}
