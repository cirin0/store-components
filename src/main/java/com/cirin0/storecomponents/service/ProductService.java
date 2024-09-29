package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProductsByCategory(String categoryName) {
    return productRepository.findByCategory_Name(categoryName);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
  }
}
