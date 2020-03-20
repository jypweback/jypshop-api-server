package com.jypshop.dto;

import com.jypshop.domain.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class OrderItemResponse {

    private String name;

    private int count;

    private int orderPrice;

    @Builder
    public OrderItemResponse(String name, int count, int orderPrice) {
        this.name = name;
        this.count = count;
        this.orderPrice = orderPrice;
    }
}
