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
 * Description :
 */

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void connectParentCategory(Category parent){
        this.parent = parent;
    }

    public void update(Category category){
        this.name = category.getName();
    }

    @Builder
    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
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
