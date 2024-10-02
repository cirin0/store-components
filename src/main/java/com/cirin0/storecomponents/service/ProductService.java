package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product updatedProduct) {
    return productRepository.findById(id)
        .map(product -> {
          product.setName(updatedProduct.getName());
          product.setDescription(updatedProduct.getDescription());
          product.setPrice(updatedProduct.getPrice());
          product.setCategory(updatedProduct.getCategory());
          return productRepository.save(product);
        })
        .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
