package com.jypshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/07
 * Github : http://github.com/jypweback
 */

@Getter
@NoArgsConstructor
@Entity
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /**
     * 연관관계 편의 메소드
     */
    public void setCategory(Category category){

        if(this.category != null){
            this.category.removeCategoryItem(this);
        }

        this.category = category;
        this.category.addCategoryItem(this);
    }

    public void setItem(Item item){

        if(this.item != null){
            this.item.removeCategoryItem(this);
        }

        this.item = item;
        this.item.addCategoryItem(this);
    }

}
