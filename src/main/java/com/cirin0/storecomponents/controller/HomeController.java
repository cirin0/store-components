package com.cirin0.storecomponents.controller;

import org.springframework.ui.Model;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {
  private final ProductService productService;

  public HomeController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public String home(Model model) {
    List<Product> products = productService.getAllProducts();
    model.addAttribute("products", products);
    return "home";
  }
}
