package com.jypshop.service;

import com.jypshop.domain.Category;
import com.jypshop.dto.CategoryDto;
import com.jypshop.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto){

        Category category = categoryRepository.save(categoryDto.toEntity());

        /** 부모 카테고리 연결 */
        if(categoryDto.getParentCategoryId() != null){
            Category parent = getCategory(categoryDto.getParentCategoryId());
            category.connectParentCategory(parent);
        }

        return convertEntityToDto(category);
    }

    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto){
        Category category = getCategory(categoryDto.getCategoryId());
        category.update(categoryDto.toEntity());

        /** 부모 카테고리 연결 */
        if(categoryDto.getParentCategoryId() != null){
            Category parent = getCategory(categoryDto.getParentCategoryId());
            category.connectParentCategory(parent);
        }

        return convertEntityToDto(category);
    }

    @Transactional
    public void removeCategory(Long id){
        // TODO 하위 카테고리도 같이 삭제되도록 작업해야됨
        categoryRepository.delete(getCategory(id));
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategoryDto(Long id){
        return convertEntityToDto(getCategory(id));
    }

    private Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " 카테고리가 존재하지 않습니다."));
    }

    /**
     * Category 엔티티를 dto로 변환한다.
     * @param category
     * @return
     */
    private CategoryDto convertEntityToDto(Category category){
        return CategoryDto
                .builder()
                .categoryId(category.getId())
                .parentCategoryId(category.getParent() != null ? category.getParent().getId() : null)
                .name(category.getName())
                .build();
    }

}
