package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.CategoryRequestDTO;
import com.cirin0.storecomponents.dto.CategoryDTO;
import com.cirin0.storecomponents.model.Category;
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

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Category category = categoryService.getCategoryById(id);
    return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
    CategoryDTO categoryDTO = categoryService.createCategory(categoryRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
    Optional<CategoryDTO> updatedCategory = categoryService.updateCategory(id, categoryRequestDTO);
    if (updatedCategory.isPresent()) {
      return ResponseEntity.ok(updatedCategory.get());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
