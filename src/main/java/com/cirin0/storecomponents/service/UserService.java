package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.CartDTO;
import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.mapper.CartMapper;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.Role;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final CartMapper cartMapper;

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toDto)
        .toList();
  }

  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    return userMapper.toDto(user);
  }

  public void registerUser(UserRegister userRegister) {
    User user = userMapper.toRegisterEntity(userRegister);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    user.setCreatedAt(LocalDateTime.now());
    Cart cart = Cart.builder()
        .user(user)
        .totalPrice(0.0)
        .items(new ArrayList<>())
        .build();
    user.setCart(cart);
    userRepository.save(user);
  }

  public User getCurrentUser(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email " + email));
  }

//  public void loginUser(UserRegister userRegister) {
//    User user = userRepository.findByEmail(userRegister.getEmail())
//        .orElseThrow(() -> new RuntimeException("User not found with email " + userRegister.getEmail()));
//    if (!passwordEncoder.matches(userRegister.getPassword(), user.getPassword())) {
//      throw new RuntimeException("Invalid password");
//    }
////    UserRegister userDTO = userMapper.toRegisterDTO(user);
//  }

//  public UserDTO loginUser(UserRegister userRegister) {
//    User user = userRepository.findByEmail(userRegister.getEmail())
//        .orElseThrow(() -> new RuntimeException("User not found with email " + userRegister.getEmail()));
//    if (!encoder().matches(userRegister.getPassword(), user.getPassword())) {
//      throw new RuntimeException("Invalid password");
//    }
//    return userMapper.toDTO(user);
//  }

  public UserUpdate updateUser(Long id, UserUpdate userUpdate) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    userMapper.partialUpdate(userUpdate, user);
    return userMapper.toUpdateDTO(userRepository.save(user));
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id " + id);
    }
    userRepository.deleteById(id);
  }

  public UserDetailsDTO getUserByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    CartDTO cart = cartMapper.toDto(user.getCart());
    return UserDetailsDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .role(user.getRole())
        .createdAt(user.getCreatedAt())
        .cart(cart)
        .orders(new ArrayList<>())
        .build();
  }
}
