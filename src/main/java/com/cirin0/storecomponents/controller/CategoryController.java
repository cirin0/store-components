package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @PostMapping("/create")
 public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category createdCategory = categoryService.createCategory(category);
    return ResponseEntity.ok(createdCategory);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Category> updateCategory(Long id, Category updatedCategory) {
    return ResponseEntity.ok(categoryService.updateCategory(id, updatedCategory));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
