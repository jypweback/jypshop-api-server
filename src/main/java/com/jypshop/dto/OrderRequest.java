package com.jypshop.dto;

import io.swagger.annotations.SwaggerDefinition;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/18
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class OrderRequest {

    private Long memberId;

    private List<OrderItemRequest> orderItemRequest;

    private AddressRequest addressRequest;

    @Builder
    public OrderRequest(Long memberId, List<OrderItemRequest> orderItemRequest, AddressRequest addressRequest) {
        this.memberId = memberId;
        this.orderItemRequest = orderItemRequest;
        this.addressRequest = addressRequest;
    }
}
