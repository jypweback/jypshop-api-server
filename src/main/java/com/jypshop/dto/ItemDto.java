package com.jypshop.dto;

import com.jypshop.domain.Item;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/10
 * Github : http://github.com/jypweback
 * Description :
 */

@NoArgsConstructor
@Data
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private List<Long> categoryIds = new ArrayList<>();

    public Item toEntity(){
        return Item
                .builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

    @Builder
    public ItemDto(Long id, String name, int price, int stockQuantity, List<Long> categoryIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryIds = categoryIds;
    }
}
