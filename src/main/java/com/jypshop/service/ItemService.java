package com.jypshop.service;

import com.jypshop.domain.Category;
import com.jypshop.domain.CategoryItem;
import com.jypshop.domain.Item;
import com.jypshop.dto.ItemDto;
import com.jypshop.repository.CategoryItemRepository;
import com.jypshop.repository.CategoryRepository;
import com.jypshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public ItemDto createItem(ItemDto itemDto){

        Item item = itemRepository.save(itemDto.toEntity());

        for(Long categoryId : itemDto.getCategoryIds()){
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException(categoryId + "카테고리가 존재하지 않습니다."));

            CategoryItem categoryItem = CategoryItem.builder().build();
            categoryItem.setCategory(category);
            categoryItem.setItem(item);
            categoryItemRepository.save(categoryItem);
        }

        return convertEntityToDto(item);
    }

    @Transactional
    public ItemDto updateItem(ItemDto itemDto){
        Item item = getItem(itemDto.getId());
        item.update(itemDto.toEntity());

        return convertEntityToDto(item);
    }

    private Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "상품이 존재하지 않습니다."));
    }

    private ItemDto convertEntityToDto(Item item){

        List<CategoryItem> categoryItems = item.getCategoryItems();
        List<Long> categoryIds = new ArrayList<>();
        for(CategoryItem categoryItem : categoryItems){
            if(categoryItem.getCategory() != null){
                categoryIds.add(categoryItem.getCategory().getId());
            }
        }

        return ItemDto
                .builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .categoryIds(categoryIds)
                .build();
    }
}
