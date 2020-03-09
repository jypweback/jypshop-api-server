package com.jypshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/07
 * Github : http://github.com/jypweback
 */

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Builder
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * 연관관계 편의 메소드
     */
    public void addCategoryItem(CategoryItem categoryItem){
        this.categoryItems.add(categoryItem);
    }

    public void removeCategoryItem(CategoryItem categoryItem){
        this.categoryItems.remove(categoryItem);
    }
}
