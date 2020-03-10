package com.jypshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jypshop.domain.Category;
import com.jypshop.dto.CategoryDto;
import com.jypshop.dto.ItemDto;
import com.jypshop.repository.CategoryRepository;
import com.jypshop.repository.ItemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/10
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        // 카테고리 등록
        categoryRepository.save(Category.builder().name("카테고리1").build());
        categoryRepository.save(Category.builder().name("카테고리2").build());
    }

    @After
    public void tearDown(){
        itemRepository.deleteAll();
    }

    @Test
    public void 상품및카테고리_등록() throws Exception{
        // given
        final String name = "상품1";
        final int price = 5000;
        final int stockQuantity = 5;
        final String url = "http://localhost:" + port + "/api/v1/item";

        List<Long> categoryIds = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories){
            categoryIds.add(category.getId());
        }

        ItemDto itemDto = ItemDto
                .builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .categoryIds(categoryIds)
                .build();

        // when
        ResultActions actions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(itemDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("price").value(price))
                .andExpect(jsonPath("stockQuantity").value(stockQuantity))
        ;
    }
}