package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.CategoryService;
import com.cirin0.storecomponents.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryWebController {

  private final CategoryService categoryService;
  private final ProductService productService;

  @GetMapping
  public String getAllCategories(Model model) {
    model.addAttribute("pageTitle", "Категорії");
    model.addAttribute("categories", categoryService.getAllCategories());
    return "category/category-list";
  }

  @GetMapping("/{id}")
  public String getCategoryById(@PathVariable Long id, Model model) {
    String categoryName = categoryService.getCategoryById(id).getName();
    model.addAttribute("pageTitle", categoryName);
    model.addAttribute("category", categoryService.getCategoryById(id));
    model.addAttribute("products", productService.getProductsByCategoryId(id));
    return "category/category-detail";
  }
}
