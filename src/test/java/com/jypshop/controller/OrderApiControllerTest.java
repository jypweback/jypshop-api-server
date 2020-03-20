package com.jypshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jypshop.domain.*;
import com.jypshop.dto.AddressRequest;
import com.jypshop.dto.OrderItemRequest;
import com.jypshop.dto.OrderRequest;
import com.jypshop.repository.ItemRepository;
import com.jypshop.repository.MemberRepository;
import com.jypshop.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    private Member member;

    private Item item;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        memberRepository.save(
                Member.builder()
                        .name("박제영")
                        .address(Address.builder()
                                .zipcode("zipcode")
                                .street("street")
                                .city("city")
                                .build())
                        .build());

        itemRepository.save(
                Item.builder()
                        .name("아이템1")
                        .price(500)
                        .stockQuantity(10)
                        .build());

        member = memberRepository.findAll().get(0);
        item = itemRepository.findAll().get(0);
    }

    @After
    public void tearDown() {
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void 상품주문_등록() throws Exception {

        // given
        int orderPrice = 5000;
        int count = 5;
        String url = "http://localhost:8080/api/v1/order";

        List<OrderItemRequest> orderItemRequestList = new ArrayList<>();
        orderItemRequestList.add(OrderItemRequest.builder()
                .itemId(item.getId())
                .orderPrice(orderPrice)
                .count(count)
                .build());

        AddressRequest addressRequest = AddressRequest.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();

        OrderRequest orderRequest = OrderRequest.builder()
                .memberId(member.getId())
                .orderItemRequest(orderItemRequestList)
                .addressRequest(addressRequest)
                .build();

        // when
        ResultActions action = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(orderRequest))
        );

        //then
        action
                .andExpect(status().isOk())
        ;

    }

    @Test(expected = NestedServletException.class)
    public void 상품등록_재고부족() throws Exception {

        // given
        int orderPrice = 5000;
        int count = Integer.MAX_VALUE;
        String url = "http://localhost:8080/api/v1/order";

        List<OrderItemRequest> orderItemRequestList = new ArrayList<>();
        orderItemRequestList.add(OrderItemRequest.builder()
                .itemId(item.getId())
                .orderPrice(orderPrice)
                .count(count)
                .build());

        AddressRequest addressRequest = AddressRequest.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();

        OrderRequest orderRequest = OrderRequest.builder()
                .memberId(member.getId())
                .orderItemRequest(orderItemRequestList)
                .addressRequest(addressRequest)
                .build();

        // when
        ResultActions action = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(orderRequest))
        );

        // then
        fail("재고수량 초과로 exception이 발생되어야한다.");
    }

    @Test
    @Transactional
    public void 상품취소() throws Exception {
        // given
        String url = "http://localhost:8080/api/v1/order/cancel";

        Order savedOrder = orderRepository.save(
                Order.createOrder(member,
                        Delivery.builder()
                                .address(Address.builder()
                                        .zipcode("zipcode")
                                        .street("street")
                                        .city("city").build()).build(),
                        Arrays.asList(OrderItem.createOrderItem(item, 5000, 5))));

        ResultActions actions = mvc.perform(put(url + "/" + savedOrder.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.CANCLE);
        actions
                .andExpect(status().isOk());
    }


}