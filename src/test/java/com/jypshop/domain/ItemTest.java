package com.jypshop.domain;

import com.jypshop.repository.CategoryItemRepository;
import com.jypshop.repository.CategoryRepository;
import com.jypshop.repository.ItemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/10
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @After
    public void cleanUp(){
        itemRepository.deleteAll();
    }

    @Before
    public void before(){
        //categoryRepository.save(Category.builder().name("카테고리").build());
    }

    @Test
    public void 상품_등록(){

        // given
        String name = "상품1";
        int price = 5000;
        int stockQuantity = 5;
        Item item = Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem.getName()).isEqualTo(name);
        assertThat(savedItem.getPrice()).isEqualTo(price);
        assertThat(savedItem.getStockQuantity()).isEqualTo(stockQuantity);
    }

    @Test
    @Transactional
    public void 상품_카테고리_등록(){

        // given
        Category category = categoryRepository.save(Category.builder().name("대표카테고리").build());

        Item item = Item.builder().name("상품1").price(5000).stockQuantity(5).build();
        CategoryItem categoryItem = CategoryItem.builder().build();
        categoryItem.setCategory(category);
        categoryItem.setItem(item);

        // when
        categoryItemRepository.save(categoryItem);
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem.getCategoryItems().size()).isEqualTo(1);
    }


}