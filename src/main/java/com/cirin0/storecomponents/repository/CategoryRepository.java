package com.cirin0.storecomponents.repository;


import com.cirin0.storecomponents.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
