package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.CategoryRepository;
import com.cirin0.storecomponents.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return Optional.ofNullable(productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id " + id)));
  }

  public Product createProduct(ProductDTO productDTO) {
    Category category = categoryRepository.findById(productDTO.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Category not found with id " + productDTO.getCategoryId()));
    Product product = new Product();
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setImageUrl(productDTO.getImageUrl());
    product.setCategory(category);
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product updatedProduct) {
    return productRepository.findById(id)
        .map(product -> {
          product.setName(updatedProduct.getName());
          product.setDescription(updatedProduct.getDescription());
          product.setPrice(updatedProduct.getPrice());
          product.setImageUrl(updatedProduct.getImageUrl());
          product.setCategory(updatedProduct.getCategory());
          return productRepository.save(product);
        })
        .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
