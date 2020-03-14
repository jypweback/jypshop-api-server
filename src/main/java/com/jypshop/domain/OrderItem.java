package com.jypshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/07
 * Github : http://github.com/jypweback
 */

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    private void updateOrderPrice(int price){
        this.orderPrice = orderPrice;
    }

    private void updateOrderCount(int count){
        this.count = count;
    }

    //== 생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.updateOrderCount(count);
        orderItem.updateOrderPrice(orderPrice);

        item.removeStock(count);

        return orderItem;
    }

    //==연관관계 메서드==//
    public void setOrder(Order order){
        if(this.order != null){
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        order.getOrderItems().add(this);
    }

    public void setItem(Item item){
        if(this.item != null){
            this.item.getOrderItems().remove(this);
        }
        this.item = item;
        item.getOrderItems().add(this);
    }

    //==비지니스 로직==/
    public void cancle(){
        getItem().addStock(count);
    }

    //==조회 로직==/

    /**
     * 주문상품 전체 조회
     */
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }

}
