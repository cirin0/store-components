package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Category;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.service.CategoryService;
import com.cirin0.storecomponents.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Product> getProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @GetMapping("/categories")
  public List<Category> getCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/category/{id}")
  public Category getCategoryById(@PathVariable Long id) {
    return categoryService.getCategoryById(id);
  }


}
