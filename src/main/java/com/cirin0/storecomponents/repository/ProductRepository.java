package com.cirin0.storecomponents.repository;

import com.cirin0.storecomponents.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategory_Name(String categoryName);
}
