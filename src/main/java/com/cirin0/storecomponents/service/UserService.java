package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

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
    if (userRepository.existsByEmail(userRegister.getEmail())) {
      throw new RuntimeException("Email is already taken");
    }
    User user = userMapper.toRegisterEntity(userRegister);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Cart cart = Cart.builder()
        .user(user)
        .totalPrice(BigDecimal.ZERO)
        .items(new ArrayList<>())
        .build();
    user.setCart(cart);
    userMapper.toRegisterDTO(userRepository.save(user));
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

  public UserRegister getUserByEmail(String username) {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new RuntimeException("User not found with email " + username));
    return userMapper.toRegisterDTO(user);
  }


}
