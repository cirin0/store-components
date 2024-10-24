package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.CategoryDTO;
import com.cirin0.storecomponents.dto.CategoryRequest;
import com.cirin0.storecomponents.mapper.CategoryMapper;
import com.cirin0.storecomponents.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    CategoryDTO category = categoryService.getCategoryById(id);
    return Optional.ofNullable(category)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequest categoryRequest) {
    CategoryDTO categoryDTO = categoryService.createCategory(categoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
    CategoryDTO categoryDTO = categoryService.updateCategory(id, categoryRequest);
    return ResponseEntity.ok(categoryDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
