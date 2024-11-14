package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.service.CartService;
import com.cirin0.storecomponents.service.ProductService;
import com.cirin0.storecomponents.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartWebController {

  private final CartService cartService;
  private final UserService userService;
  private final ProductService productService;

  @GetMapping
  public String getCart(Principal authentication, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    CartDTO cart = cartService.getCartByUserId(user.getId());
    model.addAttribute("cart", cart);
    return "cart";
  }

  @PostMapping("/add-product")
  public String addProductToCart(Principal authentication, Long productId, AddToCartDTO addToCartDTO, RedirectAttributes redirectAttributes) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    cartService.addToCart(user.getId(), addToCartDTO);
    String productName = productService.getProductNameById(productId).getName();
    redirectAttributes.addFlashAttribute("successMessage", "Товар додано до кошика");
    return "redirect:/products/" + productId;
  }

  @PostMapping("/remove-product/{productId}")
  public String removeProductFromCart(Principal authentication, @PathVariable Long productId, RedirectAttributes redirectAttributes) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    cartService.removeFromCart(user.getId(), productId);
    return "redirect:/cart";
  }

  @PostMapping("/clear-cart")
  public String clearCart(Principal authentication, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    cartService.clearCart(user.getId());
    return "redirect:/cart";
  }

  @PostMapping("update-product-quantity/{productId}")
  public String updateProductQuantity(Principal authentication, @PathVariable Long productId, Integer quantity, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    cartService.updateCartItemQuantity(user.getId(), productId, quantity);
    return "redirect:/cart";
  }
}
