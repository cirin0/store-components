package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductWebController {

  private final ProductService productService;

  @GetMapping
  public String getAllProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "product-list";
  }

  @GetMapping("/{id}")
  public String getProductDetails(@PathVariable Long id, Model model) {
    model.addAttribute("product", productService.getProductById(id).orElseThrow());
    return "product-details";
  }
}
