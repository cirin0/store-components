package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.ProductDTO;
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

  private ProductDTO verifyAndUpsert(ProductDTO productDTO, Product product) { //
    if (productDTO.getCategoryId() != null) {
      Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
      if (categoryOptional.isEmpty()) {
        throw new EntityNotFoundException("Category not found with id " + productDTO.getCategoryId());
      }
      product.setCategory(categoryOptional.get());
    }
    Product savedProduct = productRepository.save(product);
    return productMapper.toDTO(savedProduct);
  }

  public List<ProductDTO> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return productMapper.toDTOList(products);
  }

  public ProductDTO getProductById(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDTO)
        .orElse(null);
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product = productMapper.toEntity(productDTO);
    return verifyAndUpsert(productDTO, product);
  }

  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isEmpty()) {
      throw new IllegalArgumentException("Product not found with id " + id);
    }
    Product product = productOptional.get();
    if (productDTO.getPrice() != null) {
      product.setPrice(productDTO.getPrice());
    }
    productMapper.updateProductFromDTO(productDTO, product);
    return verifyAndUpsert(productDTO, product);
  }

  public void deleteProduct(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found " + id));
    productRepository.delete(product);
  }
}
