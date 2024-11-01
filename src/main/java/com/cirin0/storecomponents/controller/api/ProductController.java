package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.mapper.ProductMapper;
import com.cirin0.storecomponents.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    ProductDTO product = productService.getProductById(id);
    return Optional.ofNullable(product)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductsByCategoryId(id));
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    ProductDTO createdProductDTO = productService.createProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
    return ResponseEntity.ok(updatedProductDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
