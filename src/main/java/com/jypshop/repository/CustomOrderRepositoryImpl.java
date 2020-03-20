package com.jypshop.repository;

import com.jypshop.domain.Order;
import static com.jypshop.domain.QOrder.order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/20
 * Github : http://github.com/jypweback
 * Description :
 */

@Repository
public class CustomOrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    private final JPAQueryFactory queryFactory;

    public CustomOrderRepositoryImpl(JPAQueryFactory queryFactory){
        super(Order.class);
        this.queryFactory = queryFactory;
    }

    public List<Order> findOrderList(){
        return queryFactory.selectFrom(order).fetch();
    }

}
