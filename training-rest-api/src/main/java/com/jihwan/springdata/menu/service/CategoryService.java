package com.jihwan.springdata.menu.service;

import com.jihwan.springdata.menu.entity.Category;
import com.jihwan.springdata.menu.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
}
