package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.service.CartService;
import com.cirin0.storecomponents.service.ProductService;
import com.cirin0.storecomponents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartService cartService;

  @Autowired
  private ProductService productService;

  @Autowired
  private UserService userService;

  @GetMapping
  public String showNotLoggedInMessage() {
    return "Please log in to access your cart";
  }

  @PostMapping("/{cartId}/products/{productId}")
  public String addToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
    Product product = productService.getProductById(productId);
    cartService.addToCart(cartId, product, quantity);
    return "Product added to cart";
  }

  @DeleteMapping("/{cartId}/remove/{productId}")
  public String removeFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
    cartService.removeFromCart(cartId, productId);
    return "Product removed from cart";
  }

  @GetMapping("/{cartId}/total")
  public double getCartTotal(@PathVariable Long cartId) {
    return cartService.getCartsTotalPrice(cartId);
  }

  @GetMapping("/{cartId}/clear")
  public String clearCart(@PathVariable Long cartId) {
    cartService.clearCart(cartId);
    return "Cart cleared";
  }
}
