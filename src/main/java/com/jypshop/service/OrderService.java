package com.jypshop.service;

import com.jypshop.repository.ItemRepository;
import com.jypshop.repository.MemberRepository;
import com.jypshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        return null;
    }

}
