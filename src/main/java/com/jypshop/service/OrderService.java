package com.jypshop.service;

import com.jypshop.domain.*;
import com.jypshop.dto.*;
import com.jypshop.repository.ItemRepository;
import com.jypshop.repository.MemberRepository;
import com.jypshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/14
 * Github : http://github.com/jypweback
 * Description :
 */

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 상품주문
     */
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        // 회원
        Member member = memberRepository.findById(orderRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException(orderRequest.getMemberId() + " 회원이 존재하지 않습니다."));

        // 상품
        List<OrderItem> orderItemList = new ArrayList<>();
        orderRequest.getOrderItemRequest().stream().forEach((orderItemReq) -> {
            Item item = itemRepository.findById(orderItemReq.getItemId()).orElseThrow(
                    () -> new IllegalArgumentException(orderItemReq.getItemId() + " 상품이 존재하지 않습니다.")
            );
            orderItemList.add(
                    OrderItem.createOrderItem(item, orderItemReq.getOrderPrice(), orderItemReq.getCount())
            );
        });

        // 주소
        Delivery delivery = Delivery.builder()
                .address(orderRequest.getAddressRequest().toEntity())
                .build();

        // 상품주문
        Order savedOrder = orderRepository.save(Order.createOrder(member, delivery, orderItemList));
        return convertOrderToOrder(savedOrder);
    }

    /**
     * 상품주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        getOrder(orderId).cancel();
    }

    /**
     * 상품주문 response 조회
     */
    @Transactional(readOnly = true)
    public OrderResponse getOrderResponse(Long orderId) {
        return convertOrderToOrder(getOrder(orderId));
    }

    /**
     * 상품주문 entity 조회
     */
    @Transactional(readOnly = true)
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException(orderId + " 주문정보가 존재하지 않습니다."));
    }

    /**
     * Entity to Response Object
     */
    private AddressResponse convertAddressToAddress(Address address) {
        return AddressResponse
                .builder()
                .city(address.getCity())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

    private OrderResponse convertOrderToOrder(Order order){

        // 회원
        Member member = order.getMember();
        Address address = member.getAddress();

        // 주문상품
        List<OrderItem> orderItems = order.getOrderItems();

        // 주소
        Delivery delivery = order.getDelivery();
        Address deliveryAddress = delivery.getAddress();

        // 회원 주소
        AddressResponse memAddressResponse = convertAddressToAddress(address);
        // 주문상품 주소
        AddressResponse deliveryAddressResponse = convertAddressToAddress(deliveryAddress);

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .memberResponse(MemberResponse.builder()
                        .addressResponse(memAddressResponse)
                        .memberId(member.getId())
                        .name(member.getName())
                        .build())
                .deliveryResponse(DeliveryResponse.builder()
                        .addressResponse(deliveryAddressResponse)
                        .deliveryStatus(delivery.getStatus())
                        .deliveryId(delivery.getId())
                        .build())
                .build();
    }

}
