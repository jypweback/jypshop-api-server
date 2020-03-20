package com.jypshop.repository;

import com.jypshop.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/20
 * Github : http://github.com/jypweback
 * Description :
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    private Member member;

    private Item item;

    @Before
    public void before(){

        member = memberRepository.save(
                Member.builder()
                        .name("박제영")
                        .address(Address.builder()
                                .zipcode("zipcode")
                                .street("street")
                                .city("city")
                                .build())
                        .build());

        item = itemRepository.save(
                Item.builder()
                        .name("아이템1")
                        .price(500)
                        .stockQuantity(10)
                        .build());
    }

    @Test
    @Transactional
    public void queryDsl_연동테스트(){

        // given
        orderRepository.save(
                Order.createOrder(member,
                        Delivery.builder()
                                .address(Address.builder()
                                        .zipcode("zipcode")
                                        .street("street")
                                        .city("city").build()).build(),
                        Arrays.asList(OrderItem.createOrderItem(item, 5000, 5))));

        // when
        List<Order> orders = orderRepository.findOrderList();

        // then
        assertThat(orders.size()).isLessThan(5);
    }

}