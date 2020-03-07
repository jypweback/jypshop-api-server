package com.jypshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TableOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
//    private List<OrderItem> orderItems = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;

    private LocalDateTime orderDate;

    // status

}
