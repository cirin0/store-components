package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.CartItemRepository;
import com.cirin0.storecomponents.repository.CartRepository;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;
  private final UserRepository userRepository;

  public Optional<Cart> getCartByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }

  public Cart getCartById(Long cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartId));
  }

  public Cart createCart(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      Cart cart = new Cart();
      cart.setUser(user);
      cart.setTotalPrice(0);
      return cartRepository.save(cart);
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }

  public void deleteCart(Long id) {
    cartRepository.deleteById(id);
  }

  public Cart addProductToCart(Long cartId, Long productId, int quantity) {
    Optional<Cart> cartOptional = cartRepository.findById(cartId);
    Optional<ProductDTO> productOptional = Optional.ofNullable(productService.getProductById(productId));

    if (cartOptional.isPresent() && productOptional.isPresent()) {
      Cart cart = cartOptional.get();
      ProductDTO product = productOptional.get();
      CartItem cartItem = cart
          .getItems()
          .stream()
          .filter(item -> item.getProduct().getId().equals(productId))
          .findFirst()
          .orElseGet(() -> {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            //newItem.setProduct(productService.getProductById(productId));
            newItem.setQuantity(0);
            newItem.setPrice(0);
            return newItem;
          });
      cartItem.setQuantity(cartItem.getQuantity() + quantity);
      cartItem.setPrice(product.getPrice() * cartItem.getQuantity());
      cartItemRepository.save(cartItem);
      updateCartTotalPrice(cart);
      return cart;
    } else {
      throw new IllegalArgumentException("Cart or Product not found");
    }
  }

  public Cart UpdateCartItemQuantity(Long cartId, Long cartItemId, int newQuantity) {
    Optional<Cart> cartOptional = cartRepository.findById(cartId);
    Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

    if (cartOptional.isPresent() && cartItemOptional.isPresent()) {
      Cart cart = cartOptional.get();
      CartItem cartItem = cartItemOptional.get();
      if (newQuantity <= 0) {
        cartItemRepository.deleteById(cartItemId);
      } else {
        cartItem.setQuantity(newQuantity);
        cartItem.setPrice(cartItem.getProduct().getPrice() * newQuantity);
        cartItemRepository.save(cartItem);
      }
      updateCartTotalPrice(cart);
      return cart;
    } else {
      throw new IllegalArgumentException("Cart or CartItem not found");
    }
  }

  public void removeCartItem(Long cartItemId) {
    cartItemRepository.deleteById(cartItemId);
  }

  public void clearCart(Long cartId) {
    Optional<Cart> cartOptional = cartRepository.findById(cartId);
    if (cartOptional.isPresent()) {
      Cart cart = cartOptional.get();
      cart.getItems().clear();
      cart.setTotalPrice(0);
      cartRepository.save(cart);
    } else {
      throw new IllegalArgumentException("Cart not found");
    }
  }

  private void updateCartTotalPrice(Cart cart) {
    double totalPrice = cart.getItems().stream()
        .mapToDouble(CartItem::getPrice)
        .reduce(0.0, Double::sum);
    cart.setTotalPrice(totalPrice);
    cartRepository.save(cart);
  }

  public double calculateTotalPrice(Long cartId) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + cartId));
    return cart.getItems().stream()
        .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
        .sum();
  }
}
