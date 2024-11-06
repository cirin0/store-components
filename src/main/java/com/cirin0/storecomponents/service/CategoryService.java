package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.category.CategoryCreate;
import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.mapper.CategoryMapper;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryDTO> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDTOList(categories);
  }

  public CategoryDTO getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .map(categoryMapper::toDTO)
        .orElse(null);
  }

  public CategoryDTO createCategory(CategoryCreate categoryCreate) {
    Category category = categoryMapper.toEntity(categoryCreate);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toDTO(savedCategory);
  }

  public CategoryDTO updateCategory(Long id, CategoryCreate categoryCreate) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isEmpty()) {
      throw new RuntimeException("Category not found with id " + id);
    }
    Category category = categoryOptional.get();
    categoryMapper.updateCategoryFromDTO(categoryCreate, category);
    Category updatedCategory = categoryRepository.save(category);
    return categoryMapper.toDTO(updatedCategory);
  }

  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    categoryRepository.delete(category);
  }
}
