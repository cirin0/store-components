package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists");
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    }
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}
