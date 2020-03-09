package com.jypshop.controller;

import com.jypshop.dto.CategoryDto;
import com.jypshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/category")
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping("/category/{id}")
    public void removeCategory(@PathVariable Long id){
        categoryService.removeCategory(id);
    }

    @GetMapping("/category/{id}")
    public CategoryDto getCategory(@PathVariable Long id){
        return categoryService.getCategoryDto(id);
    }
}
