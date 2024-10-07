package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public void updateCategory(Long id, Category updatedCategory) {
    categoryRepository.findById(id)
        .map(category -> {
          category.setName(updatedCategory.getName());
          category.setUrlImage(updatedCategory.getUrlImage());
          return categoryRepository.save(category);
        })
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
  }

  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    categoryRepository.delete(category);
  }
}
