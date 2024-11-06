package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  private BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toDTO)
        .toList();
  }

  public List<User> allUsers() {
    return new ArrayList<>(userRepository.findAll());
  }

  public UserDTO getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new RuntimeException("User not found with id " + id);
    }
    return userMapper.toDTO(user.get());
  }

  public UserDTO registerUser(UserRegister userRegister) {
    if (userRepository.existsByEmail(userRegister.getEmail())) {
      throw new RuntimeException("Email is already taken");
    }
    User user = userMapper.toEntity(userRegister);
    user.setPassword(encoder().encode(userRegister.getPassword()));
    User savedUser = userRepository.save(user);
    return userMapper.toDTO(savedUser);
  }

  public UserDTO loginUser(UserRegister userRegister) {
    User user = userRepository.findByEmail(userRegister.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found with email " + userRegister.getEmail()));
    if (!encoder().matches(userRegister.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }
    return userMapper.toDTO(user);
  }

  public UserDTO updateUser(Long id, UserUpdate userUpdate) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    userMapper.updateEntity(userUpdate, user);
    User updatedUser = userRepository.save(user);
    return userMapper.toDTO(updatedUser);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id " + id);
    }
    userRepository.deleteById(id);
  }
}
