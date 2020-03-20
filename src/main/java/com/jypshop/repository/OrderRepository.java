package com.jypshop.repository;

import com.jypshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/14
 * Github : http://github.com/jypweback
 * Description :
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {
}
