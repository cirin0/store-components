package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.ProductDTO;
import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.mapper.CartMapper;
import com.cirin0.storecomponents.mapper.ProductMapper;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.CartItem;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.CartRepository;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ProductService productService;
  private final UserRepository userRepository;

  public CartDTO getCartByUserId(Long id) {
    Cart cart = cartRepository.findByUserId(id)
        .orElseThrow(() -> new RuntimeException("Cart not found " + id));
    return CartMapper.INSTANCE.toDto(cart);
  }

  public CartDTO getCartById(Long id) {
    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cart not found " + id));
    return CartMapper.INSTANCE.toDto(cart);
  }

  public CartDTO createCart(Long userId) {
    Optional<UserDTO> userDTO = userRepository.findById(userId)
        .map(UserMapper.INSTANCE::userToUserDTO);
    if (userDTO.isPresent()) {
      User user = UserMapper.INSTANCE.userDTOToUser(userDTO.get());
      Cart cart = new Cart();
      cart.setUser(user);
      return CartMapper.INSTANCE.toDto(cartRepository.save(cart));
    } else {
      throw new RuntimeException("User not found " + userId);
    }
  }

  public CartDTO addItemsToCart(Long userId, Long productId, int quantity) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseGet(() -> createCartForUser(userId));
    ProductDTO product = productService.getProductById(productId);
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElseGet(() -> {
          CartItem newItem = new CartItem();
          newItem.setCart(cart);
          newItem.setProduct(ProductMapper.INSTANCE.toEntity(product));
          return newItem;
        });
    cartItem.setQuantity(cartItem.getQuantity() + quantity);
    updateCartTotalPrice(cart);
    return CartMapper.INSTANCE.toDto(cart);
  }

  public CartDTO UpdateCartItemQuantity(Long userId, Long cartItemId, int quantity) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getId().equals(cartItemId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("CartItem not found " + cartItemId));
    if (quantity == 0) {
      cart.getItems().remove(cartItem);
    } else {
      cartItem.setQuantity(quantity);
    }
    updateCartTotalPrice(cart);
    return CartMapper.INSTANCE.toDto(cart);
  }

  public void deleteItemFromCart(Long userId, Long cartItemId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + userId));
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getId().equals(cartItemId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("CartItem not found " + cartItemId));
    cart.getItems().remove(cartItem);
    updateCartTotalPrice(cart);
    cartRepository.save(cart);
  }

  private Cart createCartForUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found " + userId));
    user.setId(userId);
    Cart cart = new Cart();
    cart.setUser(user);
    return cart;
  }

  private void updateCartTotalPrice(Cart cart) {
    double totalPrice = cart.getItems().stream()
        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
        .sum();
    cart.setTotalPrice(totalPrice);
  }

  public double calculateTotalPrice(Long cartId) {
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found " + cartId));
    return cart.getItems().stream()
        .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
        .sum();
  }
}
