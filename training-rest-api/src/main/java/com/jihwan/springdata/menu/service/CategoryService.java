package com.jihwan.springdata.menu.service;

import com.jihwan.springdata.menu.dto.CategoryDTO;
import com.jihwan.springdata.menu.entity.Category;
import com.jihwan.springdata.menu.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        List<Category> category = categoryRepository.findAll();
        return category;
    }


    @Transactional
    public int registCategory(Category category) {
        Category category1 = categoryRepository.save(category);

        if (Objects.isNull(category1)) {
            return 0;
        } else {
            return 1;
        }
    }

    public Category findCategoryByCode(int categoryCode) {
        Category category = categoryRepository.findById(categoryCode);
        return category;
    }

    @Transactional
    public int update(Category category, CategoryDTO categoryDTO) {


//        category.setCategoryCode(categoryDTO.getCategoryCode());
        if (!Objects.isNull(categoryDTO.getCategoryName())) {
            category.setCategoryName(categoryDTO.getCategoryName());
        }
        if (!Objects.isNull(categoryDTO.getRefCategoryCode())) {
            category.setRefCategoryCode(categoryDTO.getRefCategoryCode());
        }
        Category result = categoryRepository.save(category);
        System.out.println(result);
        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Transactional
    public int deleteCategory(int categoryCode) {
        categoryRepository.deleteById(categoryCode);
        Category category = categoryRepository.findById(categoryCode);
        if (Objects.isNull(category)) {
            return 1;
        } else {
            return 0;
        }
    }
}
