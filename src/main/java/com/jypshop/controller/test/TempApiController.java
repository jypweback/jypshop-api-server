package com.jypshop.controller.test;

import com.jypshop.domain.Category;
import com.jypshop.domain.CategoryItem;
import com.jypshop.domain.Item;
import com.jypshop.dto.MemberDto;
import com.jypshop.repository.CategoryRepository;
import com.jypshop.repository.ItemRepository;
import com.jypshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@RestController
public class TempApiController {

    private final MemberService memberService;

    private final CategoryRepository categoryRepository;

    private final ItemRepository itemRepository;

    @GetMapping("/member/insert")
    public MemberDto tempMemberInsert(){
        return memberService.createMember(MemberDto.builder()
                .name("박제영")
                .city("city")
                .street("street")
                .zipcode("zip code")
                .build()
        );
    }

    @GetMapping("/category/insert")
    public void tempCategoryInsert(){
        categoryRepository.save(Category.builder().name("대표카테고리").build());
    }

    @GetMapping("/item/insert")
    public void itemInsert(){

        Category category = categoryRepository.findAll().get(0);

        Item item = Item.builder().name("상품1").build();
        CategoryItem categoryItem = CategoryItem.builder().build();

        categoryItem.setItem(item);
        categoryItem.setCategory(category);

        itemRepository.save(item);
    }

}
