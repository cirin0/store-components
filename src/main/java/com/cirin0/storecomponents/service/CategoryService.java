package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.CategoryRequestDTO;
import com.cirin0.storecomponents.dto.CategoryDTO;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public CategoryDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
    Category category = new Category();
    category.setName(categoryRequestDTO.getName());
    category.setImageUrl(categoryRequestDTO.getImageUrl());
    Category savedCategory = categoryRepository.save(category);

    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(savedCategory.getId());
    categoryDTO.setName(savedCategory.getName());
    categoryDTO.setImageUrl(savedCategory.getImageUrl());
    categoryDTO.setProduct(savedCategory.getProducts());
    return categoryDTO;
  }

  public Optional<CategoryDTO> updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      Category categoryUpdate = category.get();
      categoryUpdate.setName(categoryRequestDTO.getName());
      categoryUpdate.setImageUrl(categoryRequestDTO.getImageUrl());
      Category updatedCategory = categoryRepository.save(categoryUpdate);

      CategoryDTO categoryDTO = new CategoryDTO();
      categoryDTO.setId(updatedCategory.getId());
      categoryDTO.setName(updatedCategory.getName());
      categoryDTO.setImageUrl(updatedCategory.getImageUrl());
      categoryDTO.setProduct(updatedCategory.getProducts());
      return Optional.of(categoryDTO);
    }
    return Optional.empty();
  }

/*
  public Optional<CategoryDTO> updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      Category categoryToUpdate = category.get();
      categoryToUpdate.setName(categoryRequestDTO.getName());
      categoryToUpdate.setImageUrl(categoryRequestDTO.getImageUrl());
      Category updatedCategory = categoryRepository.save(categoryToUpdate);
      return Optional.of(mapToCategoryDTO(updatedCategory));
    }
    return Optional.empty();
  }
/*
  private CategoryDTO mapToCategoryDTO(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(category.getId());
    categoryDTO.setName(category.getName());
    categoryDTO.setImageUrl(category.getImageUrl());
    categoryDTO.setProductNames(category
        .getProducts()
        .stream()
        .map(Product::getName)
        .collect(Collectors
            .toList()));
    return categoryDTO;
  }
  */

/*
  public CategoryDTO createCategory(CategoryAddDTO categoryAddDTO) {
    Category category = new Category();
    category.setId(categoryAddDTO.getId());
    category.setName(categoryAddDTO.getName());
    category.setImageUrl(categoryAddDTO.getImageUrl());
    Category savedCategory = categoryRepository.save(category);

    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(savedCategory.getId());
    categoryDTO.setName(savedCategory.getName());
    categoryDTO.setImageUrl(savedCategory.getImageUrl());
    return categoryDTO;
  }

  public CategoryDTO updateCategory(Long id, CategoryAddDTO categoryAddDTO) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    category.setName(categoryAddDTO.getName());
    category.setImageUrl(categoryAddDTO.getImageUrl());
    Category updatedCategory = categoryRepository.save(category);

    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(updatedCategory.getId());
    categoryDTO.setName(updatedCategory.getName());
    categoryDTO.setImageUrl(updatedCategory.getImageUrl());
    return categoryDTO;
  }

 */

  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    categoryRepository.delete(category);
  }
}
