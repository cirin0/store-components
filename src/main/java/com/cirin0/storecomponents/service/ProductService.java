package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.mapper.CategoryMapper;
import com.cirin0.storecomponents.mapper.ProductMapper;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.CategoryRepository;
import com.cirin0.storecomponents.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;
  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  private ProductDTO verifyAndUpsert(ProductDTO productDTO, Product product) {
    if (productDTO.getCategoryId() != null) {
      Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
      if (categoryOptional.isEmpty()) {
        throw new EntityNotFoundException("Category not found with id " + productDTO.getCategoryId());
      }
      product.setCategory(categoryOptional.get());
    }
    Product savedProduct = productRepository.save(product);
    return productMapper.toDto(savedProduct);
  }

  public List<ProductDTO> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return productMapper.toDtoList(products);
  }

  public ProductDTO getProductById(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDto)
        .orElse(null);
  }

  public List<ProductDTO> getProductsByCategoryId(Long id) {
    List<Product> products = productRepository.findByCategoryId(id);
    return productMapper.toDtoList(products);
  }

  public ProductDTO getProductNameById(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDto)
        .orElse(null);
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    CategoryDTO category = categoryService.getCategoryById(productDTO.getCategoryId());
    Product product = productMapper.toEntity(productDTO);
    product.setCategory(Category.builder()
        .id(category.getId())
        .name(category.getName())
        .build());
    Product savedProduct = productRepository.save(product);
    return productMapper.toDto(savedProduct);
  }

  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    CategoryDTO categoryDTO = categoryService.getCategoryById(productDTO.getCategoryId());
    Product updatedProduct = productMapper.partialUpdate(productDTO, product);
    product.setCategory(Category.builder()
        .id(categoryDTO.getId())
        .name(categoryDTO.getName())
        .build());
    return productMapper.toDto(productRepository.save(updatedProduct));
  }

  public void deleteProduct(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found " + id));
    productRepository.delete(product);
  }
}
