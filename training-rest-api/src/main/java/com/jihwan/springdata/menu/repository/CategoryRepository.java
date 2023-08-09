package com.jihwan.springdata.menu.repository;

import com.jihwan.springdata.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int categorycode);
}
