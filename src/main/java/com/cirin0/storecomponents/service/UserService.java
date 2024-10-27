package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRegister;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.model.Role;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

  public UserDTO getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new RuntimeException("User not found with id " + id);
    }
    return userMapper.toDTO(user.get());
  }

  public UserDTO createUser(UserRegister userRegister) {
    User user = userMapper.toEntity(userRegister);
    user.setPassword(encoder().encode(userRegister.getPassword()));
    user.setRole(userRegister.getRole() == null ? Role.USER : userRegister.getRole());
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

  public UserDTO updateUser(Long id, UserRegister userRegister) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty()) {
      throw new RuntimeException("User not found with id " + id);
    }
    User user = userOptional.get();
    user.setEmail(userRegister.getEmail());
    user.setPassword(encoder().encode(userRegister.getPassword()));
    user.setRole(userRegister.getRole() == null ? Role.USER : userRegister.getRole());
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
