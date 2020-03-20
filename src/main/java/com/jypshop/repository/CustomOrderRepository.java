package com.jypshop.repository;

import com.jypshop.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/20
 * Github : http://github.com/jypweback
 * Description :
 */

public interface CustomOrderRepository {

    List<Order> findOrderList();
}
