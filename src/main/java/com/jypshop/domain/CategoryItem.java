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

    @Builder
    public CategoryItem(){ }

    /**
     * 연관관계 편의 메소드
     */
    public void setCategory(Category category){

        if(this.category != null){
            this.category.getCategoryItems().remove(this);
        }

        this.category = category;
        category.getCategoryItems().add(this);
    }

    public void setItem(Item item){

        if(this.item != null){
            this.item.getCategoryItems().remove(this);
        }

        this.item = item;
        item.getCategoryItems().add(this);
    }

}
