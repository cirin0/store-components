package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.CategoryRequestDTO;
import com.cirin0.storecomponents.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryWebController {

  private final CategoryService categoryService;

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
    return "category-detail";
  }

  @GetMapping("/new")
  public String showAddCategoryPage(Model model) {
    CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
    model.addAttribute("pageTitle", "Нова категорія");
    model.addAttribute("category", categoryRequestDTO);
    return "create-category";
  }

  @PostMapping("/new")
  public String createCategory(@ModelAttribute("category") @Valid CategoryRequestDTO categoryRequestDTO, WebRequest request) {
    categoryService.createCategory(categoryRequestDTO);
    return "redirect:/categories";
  }

}
