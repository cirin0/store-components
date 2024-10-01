package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.Cart;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
/*
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public User registerUser(String username, String email, String password) {
    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("Email already taken");
    }
    String encodedPassword = passwordEncoder.encode(password);
    User user = new User(username, email, encodedPassword);
    return userRepository.save(user);
  }

  public User loginUser(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("Invalid password");
    }
    return user;
  }

  //Cart getOrCreateCartForUser(User user) {
  //  return cartRepository.findByUser(user).orElseGet(() -> {
  //    Cart newCart = new Cart();
  //    newCart.setUser(user);
  //    return cartRepository.save(newCart);
  //  });
  //}

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public boolean getCurrentUser() {
    return true;
  }*/
}
