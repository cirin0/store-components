package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.model.Review;
import com.cirin0.storecomponents.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ReviewService reviewService;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  // Пофіксити метод
  public Product getProductById(Long id) {
    //return productRepository.findById(id)
    //    .orElseThrow(() -> new RuntimeException("Product not found"));
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    Set<Review> reviews = Set.copyOf(reviewService.getReviewsByProductId(id));
    product.setReviews(reviews);
    return product;
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
