package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private String encryptPassword(String password) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder.encode(password);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User createUser(User user) {
    user.setPassword(encryptPassword(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(Long id, User updatedUser) {
    return userRepository.findById(id)
        .map(user -> {
          user.setEmail(updatedUser.getEmail());
          user.setPassword(encryptPassword(updatedUser.getPassword()));
          user.setFirstName(updatedUser.getFirstName());
          user.setLastName(updatedUser.getLastName());
          return userRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("User not found with id " + id));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
