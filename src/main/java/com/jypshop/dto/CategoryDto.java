package com.jypshop.dto;

import com.jypshop.domain.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@NoArgsConstructor
@Data
public class CategoryDto {

    private Long categoryId;

    private Long parentCategoryId;

    private String name;

    public Category toEntity(){
        return Category
                .builder()
                .name(this.name)
                .build();
    }

    @Builder
    public CategoryDto(Long categoryId, Long parentCategoryId, String name) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.name = name;
    }
}
