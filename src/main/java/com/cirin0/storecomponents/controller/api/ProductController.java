package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<Product> getProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Optional<Product> getProductById(@PathVariable Long id) {
    return Optional.ofNullable(productService.getProductById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id " + id)));
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
    Product createdProduct = productService.createProduct(productDTO);
    return ResponseEntity.ok(createdProduct);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
    return productService.updateProduct(id, updatedProduct);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
