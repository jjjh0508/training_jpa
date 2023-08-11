package com.jihwan.springdata.menu.controller;

import com.jihwan.springdata.menu.dto.CategoryDTO;
import com.jihwan.springdata.menu.entity.Category;
import com.jihwan.springdata.menu.entity.Menu;
import com.jihwan.springdata.menu.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorys")
@Api(value = "카테고리 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation(value = "카테고리 전체 검색 Api")
    public ResponseEntity<List<?>> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();
        if (Objects.isNull(categoryList)) {
            ResponseEntity.status(404).body("카테고리가 존재하지 않습니다.");
        }

        List<CategoryDTO> categoryDTOS = categoryList.stream().map(m-> new CategoryDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTOS);
    }

    @GetMapping("/{categoryCode}")
    @ApiOperation(value = "카테고리 단일 검색 Api")
    public ResponseEntity<Object> findCategoryByCode(@PathVariable int categoryCode) {
        Category category = categoryService.findCategoryByCode(categoryCode);
        if (Objects.isNull(category)) {
            return ResponseEntity.status(404).body("없는 카테고리입니다.");
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);

        return ResponseEntity.ok().body(categoryDTO);

    }
    @PostMapping
    @ApiOperation(value = "카테고리 등록 Api")
    public ResponseEntity<?> regist(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        int result = categoryService.registCategory(category);

        if (result > 0) {
            return ResponseEntity.ok().body("카테고리 등록 성공했습니다");
        } else {
            return ResponseEntity.status(500).body("카테고리 등록 실패했습니다.");
        }
    }

    @PutMapping
    @ApiOperation(value = "카테고리 수정 Api")
    public ResponseEntity<?> updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryService.findCategoryByCode(categoryDTO.getCategoryCode());
        if (Objects.isNull(category)) {
            return ResponseEntity.status(400).body("데이터가 존재하지 않습니다.");
        }
        int result = categoryService.update(category,categoryDTO);

        if (result > 0) {
            return ResponseEntity.ok().body("카테고리 수정에 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("카테고리 수정에 실패했습니다.");
        }
    }

    @DeleteMapping("{categoryCode}")
    @ApiOperation(value = "카테고리 삭제 Api")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryCode) {
        System.out.println(categoryCode);
        Category category = categoryService.findCategoryByCode(categoryCode);


        if (Objects.isNull(category)) {
           return ResponseEntity.status(400).body("카테고리가 존재하지 않습니다.");
        }


        if (category.getMenuList().size()>0) {
            return ResponseEntity.status(404).body("적용된 메뉴가 있습니다.");
        }
        
        int result = categoryService.deleteCategory(categoryCode);

        if (result > 0) {
            return ResponseEntity.ok().body("카테고리 삭제 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("카테고리 삭제 실패했습니다.");
        }
    }
}
