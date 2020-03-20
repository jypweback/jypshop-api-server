package com.jypshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jypshop.domain.Category;
import com.jypshop.dto.CategoryDto;
import com.jypshop.repository.CategoryRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown(){
        categoryRepository.deleteAll();
    }

    @Test
    public void 대표카테고리_등록() throws Exception{

        // given
        final String name = "대표카테고리";
        final String url = "http://localhost:" + port + "/api/v1/category";
        CategoryDto categoryDto = CategoryDto.builder().name(name).build();

        // when
        ResultActions actions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(categoryDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name));
    }

    @Test
    public void 존재하는대표카테고리를_하위카테고리에연결후_등록() throws Exception{

        // given
        Category parentCategory = categoryRepository.save(Category.builder().name("대표카테고리").build());
        final String name = "하위카테고리";
        final String url = "http://localhost:" + port + "/api/v1/category";
        final Long parentId = parentCategory.getId();
        CategoryDto childCategory = CategoryDto
                .builder()
                .name(name)
                .parentCategoryId(parentId)
                .build();

        // when
        ResultActions actions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(childCategory))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name));
    }

    @Test
    public void 카테고리_수정() throws Exception{

        // given
        Category savedCategory = categoryRepository.save(Category.builder().name("대표카테고리").build());
        final String name = "대표카테고리_업데이트";
        final String url = "http://localhost:" + port + "/api/v1/category";

        CategoryDto categoryDto = CategoryDto
                .builder()
                .categoryId(savedCategory.getId())
                .name(name)
                .build();

        // when
        ResultActions actions = mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(categoryDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
                ;
    }

    @Test
    public void 카테고리_삭제() throws Exception{

        // given
        Category savedCategory = categoryRepository.save(Category.builder().name("대표카테고리").build());
        final String url = "http://localhost:" + port + "/api/v1/category/" + savedCategory.getId();

        // when
        ResultActions actions = mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        actions.andExpect(status().isOk())
        ;
    }

    @Test
    public void 카테고리_조회() throws Exception{

        // given
        final String name = "대표카테고리";
        Category savedCategory = categoryRepository.save(Category.builder().name(name).build());
        final String url = "http://localhost:" + port + "/api/v1/category/" + savedCategory.getId();

        // when
        ResultActions actions = mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
        ;


    }
}