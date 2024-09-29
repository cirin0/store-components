package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  private static CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    CategoryService.categoryRepository = categoryRepository;
  }

  public static List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
  }
}
