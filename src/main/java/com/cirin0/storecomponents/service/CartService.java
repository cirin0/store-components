package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.Product;
import com.cirin0.storecomponents.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;

  public Cart getCartById(Long id) {
    Optional<Cart> cart = cartRepository.findById(id);
    return cart.orElseGet(Cart::new);
  }

  public void addToCart(Long cartId, Product product, int quantity){
    Cart cart = getCartById(cartId);
    CartItem cartItem = new CartItem(product, quantity);
    cart.addItem(cartItem);
    cartRepository.save(cart);
  }

  public void removeFromCart(Long cartId, Long cartItemId){
    Cart cart = getCartById(cartId);
    cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
    cartRepository.save(cart);
  }

  public void clearCart(Long cartId){
    Cart cart = getCartById(cartId);
    cart.getItems().clear();
    cartRepository.save(cart);
  }

  public double getCartsTotalPrice(Long cartId){
    Cart cart = getCartById(cartId);
    return cart.getTotalPrice();
  }
}
