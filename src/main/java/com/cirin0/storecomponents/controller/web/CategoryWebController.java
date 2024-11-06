package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.service.CategoryService;
import com.cirin0.storecomponents.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
    return "category-list";
  }

  @GetMapping("/{id}")
  public String getCategoryById(@PathVariable Long id, Model model) {
    String categoryName = categoryService.getCategoryById(id).getName();
    model.addAttribute("pageTitle", categoryName);
    model.addAttribute("category", categoryService.getCategoryById(id));
    model.addAttribute("products", productService.getProductsByCategoryId(id));
    return "category-detail";
  }

  @GetMapping("/new")
  public String showAddCategoryPage(Model model) {
    CategoryDTO categoryCreate = new CategoryDTO();
    model.addAttribute("pageTitle", "Нова категорія");
    model.addAttribute("category", categoryCreate);
    return "create-category";
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/new")
  public String createCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
    categoryService.createCategory(categoryDTO);
    return "redirect:/categories";
  }

}
