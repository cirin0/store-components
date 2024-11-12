package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.service.CartService;
import com.cirin0.storecomponents.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartWebController {

  private final CartService cartService;
  private final UserService userService;

  @GetMapping
  public String getCart(Principal authentication, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    CartDTO cart = cartService.getCartByUserId(user.getId());
    model.addAttribute("cart", cart);
    return "cart";
  }

//  @GetMapping
//  public String cart(Authentication authentication, Model model) {
//    if (authentication == null) {
//      return "redirect:/auth/login";
//    }
//    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
//    CartDTO cart = cartService.getCartByUserId(user.getId());
//    model.addAttribute("cart", cart);
//    return "cart";
//  }

//  @GetMapping("user-cart/{cartId}")
//  public String showUserCart(Model model, @PathVariable String cartId) {
//    model.addAttribute("pageTitle", "Кошик користувача");
//    return "user-cart";
//  }
}
