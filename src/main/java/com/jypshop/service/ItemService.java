package com.jypshop.service;

import com.jypshop.domain.Category;
import com.jypshop.domain.CategoryItem;
import com.jypshop.domain.Item;
import com.jypshop.dto.ItemDto;
import com.jypshop.repository.CategoryRepository;
import com.jypshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public ItemDto createItem(ItemDto itemDto){

        Item item = itemRepository.save(itemDto.toEntity());
        CategoryItem categoryItem = new CategoryItem();

        for(Long categoryId : itemDto.getCategoryIds()){
            Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException(categoryId + "카테고리가 존재하지 않습니다."));

            categoryItem.setCategory(category);
            categoryItem.setItem(item);
        }

        return null;

    }

    private Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "상품이 존재하지 않습니다."));
    }

    private ItemDto convertEntityToDto(Item item){
//        return ItemDto
//                .builder()
//                .id(item.getId())
//                .name(item.getName())
//                .price(item.getPrice())
//                .stockQuantity(item.getStockQuantity())
//                .categoryId(item.get)

        return null;
    }


}
