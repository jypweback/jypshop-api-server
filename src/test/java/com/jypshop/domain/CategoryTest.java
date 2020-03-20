package com.jypshop.domain;

import com.jypshop.repository.CategoryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanUp(){
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_등록(){
        // given
        String categoryName = "대표1_카테고리";
        categoryRepository.save(Category.builder().name(categoryName).build());

        // when
        List<Category> categoryList = categoryRepository.findAll();

        // then
        Category category = categoryList.get(0);
        assertThat(category.getName()).isEqualTo(categoryName);
    }

//    @Test
//    @Transactional
//    public void 하위카테고리등록_상위카테고리연결(){
//
//        // given
//        String childCategoryName = "대표1_카테고리_자식";
//
//        Category savedParent = categoryRepository.save(Category.builder().name("대표1_카테고리").build());
//        Category savedChild = categoryRepository.save(Category.builder().name(childCategoryName).build());
//        savedChild.connectParentCategory(savedParent);
//
//        // when
//        Category child = categoryRepository.findById(savedChild.getId()).get();
//
//        // then
//        assertThat(child.getName()).isEqualTo(childCategoryName);
//
//    }

}