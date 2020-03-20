package com.jypshop.controller;

import com.jypshop.dto.OrderRequest;
import com.jypshop.dto.OrderResponse;
import com.jypshop.dto.OrderSearchFilter;
import com.jypshop.dto.OrderSearchResponse;
import com.jypshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/18
 * Github : http://github.com/jypweback
 * Description :
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderApiController {

    private final OrderService orderService;

    /**
     * 상품주문
     * @param orderRequest
     * @return
     */
    @PostMapping("/order")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse response = orderService.createOrder(orderRequest);
        return orderService.createOrder(orderRequest);
    }

    /**
     * 상품주문 취소
     * @param orderId
     */
    @PutMapping("/order/cancel/{orderId}")
    public void cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
    }

    /**
     * 상품주문 조회
     * @param orderId
     */
    @GetMapping("/order/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId){
        return this.orderService.getOrderResponse(orderId);
    }

    @GetMapping("/order")
    public List<OrderSearchResponse> getOrderSearch(@RequestBody OrderSearchFilter orderSearchFilter){

        return null;
    }
}
