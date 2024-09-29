package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products/category/{categoryName}")
  public List<Product> getProductsByCategory(@PathVariable String categoryName) {
    return productService.getProductsByCategory(categoryName);
  }

  @GetMapping("/products/{id}")
  public Product getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }
}
