package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.CategoryService;
import com.cirin0.storecomponents.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

  private final CategoryService categoryService;
  private final ProductService productService;


  @GetMapping
  public String homePage(Model model) {
    model.addAttribute("pageTitle", "Інтернет-магазин");
    model.addAttribute("categories", categoryService.getAllCategories());
    model.addAttribute("products", productService.getAllProducts());
    return "home";
  }
}
