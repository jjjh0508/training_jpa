package com.jihwan.springdata.menu.controller;

import com.jihwan.springdata.menu.dto.MenuDTO;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuCode}")
    public ResponseEntity<Object> findMenuByCode(@PathVariable int menuCode){
        Menu menu  = menuService.findMenuByCode(menuCode);
        if (Objects.isNull(menu)) {
            return ResponseEntity.status(404).body("없는 메뉴 입니다.");
        }

        MenuDTO menuDTO = new MenuDTO(menu);
        System.out.println(menuDTO);
        return ResponseEntity.ok().body(menuDTO);

    }


    @GetMapping
    public ResponseEntity<List<?>> findAllmenu() {
        List<Menu> menuList = menuService.findAll();
        if (Objects.isNull(menuList)) {
            ResponseEntity.status(404).body("메뉴가 존재하지 않습니다.");
        }
        //엔티티 List 를 DTO List로 변환함
        List<MenuDTO> menuDTOS = menuList.stream().map(m-> new MenuDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(menuDTOS);
    }


    //localhost:8000/menus/category/4
    //?categorycode=4
    @GetMapping("/category/{categorycode}")
    public ResponseEntity<List<?>> findAllCategoryMenu(@PathVariable int categorycode) {
        System.out.println("요청");
        List<Menu> menuList = menuService.findAllCategoryMenu(categorycode);
        List<String> error = new ArrayList<>();
        error.add("해당하는 메뉴가 없습니다.");
        if (menuList.size() == 0) {
            return  ResponseEntity.status(404).body(error);
        }
        List<MenuDTO> menuDTOS = menuList.stream().map(m-> new MenuDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(menuDTOS);
    }

}
