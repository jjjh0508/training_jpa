package com.jihwan.springdata.menu.service;

import com.jihwan.springdata.menu.dto.MenuDTO;
import com.jihwan.springdata.menu.entity.Category;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.menu.repository.CategoryRepository;
import com.jihwan.springdata.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    /*
     *  begin commit 을 자동으로 수행해준다
     *  예외 발생 시 rollback 처리를 자동으로 수행해준다.
     *
     * 클래스, 메소드, 인터페이스 위에 추가하여 사용하는 것으로 , 해당 범위 내 메소드가 트랜잭션이 되도록 보장해준다.
     * 메소드를 하나의 트랜잭션(단위)로 간주하여 해당 메소드가 종요되기 전까지 어떠한 개입이나 다른 변화가 반영 되지 않도록 해준다.
     *
     * */
    @Transactional
    public int registMenu(Menu menu) {
        Menu result = menuRepository.save(menu);
        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }


    }

    @Transactional
    public int deleteCode(int menuCode) {
        menuRepository.deleteById(menuCode);
        Menu menu = menuRepository.findById(menuCode);
        if (Objects.isNull(menu)) {
            return 1;
        } else {
            return 2;
        }
    }

    @Transactional
    public int update(Menu findMenu, MenuDTO menuDTO) {
        // 넘어온 값에 이름이 있을때
        if (!Objects.isNull(menuDTO.getMenuName())) {
            findMenu.setMenuName(menuDTO.getMenuName());
            System.out.println(findMenu.getMenuName());
        }
        // 넘어온 값에 가격이 있을때 기본값이 0 이라 null 비교가 안됨
        if (menuDTO.getMenuPrice() > 0) {
            findMenu.setMenuPrice(menuDTO.getMenuPrice());
            System.out.println(findMenu.getMenuPrice());
        }
        // 넘어온 값에 카테고리코드가  있을때
        if (menuDTO.getCategoryCode() > 0) {
            findMenu.setCategoryCode(menuDTO.getCategoryCode());
            System.out.println(findMenu.getCategoryCode());
        }
        //상태가 변경되었을때
        if (!Objects.isNull(menuDTO.getOrderableStatus())) {
            findMenu.setOrderableStatus(menuDTO.getOrderableStatus());
            System.out.println(findMenu.getOrderableStatus());
        }

        Menu result = menuRepository.save(findMenu);

        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }

    }
}
