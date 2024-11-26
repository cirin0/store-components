package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.mapper.CategoryMapper;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryDTO> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDtoList(categories);
  }

  public CategoryDTO getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .map(categoryMapper::toDto)
        .orElse(null);
  }

  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    Category category = categoryMapper.toEntity(categoryDTO);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    Category updatedCategory = categoryMapper.partialUpdate(categoryDTO, category);
    return categoryMapper.toDto(categoryRepository.save(updatedCategory));
  }

  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    categoryRepository.delete(category);
  }
}
