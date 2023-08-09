package com.jihwan.springdata.menu.service;

import com.jihwan.springdata.menu.entity.Category;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.menu.repository.CategoryRepository;
import com.jihwan.springdata.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public MenuService(MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }

    public Menu findMenuByCode(int menuCode) {
        Menu menu = menuRepository.findById(menuCode);
        return menu;
    }

    public List<Menu> findAll() {
        List<Menu> menu = menuRepository.findAll();
        return menu;
    }

    public List<Menu> findAllCategoryMenu(int categorycode) {
        Category category = categoryRepository.findById(categorycode);
        List<Menu> menuList = category.getMenuList();
        return menuList;
    }
}
