package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartWebController {

  private final CartService cartService;

  @GetMapping
  public String showCart(Model model) {
    model.addAttribute("pageTitle", "Кошик");
    return "cart";
  }

  @GetMapping("user-cart/{cartId}")
  public String showUserCart(Model model, @PathVariable String cartId) {
    model.addAttribute("pageTitle", "Кошик користувача");
    return "user-cart";
  }
}
