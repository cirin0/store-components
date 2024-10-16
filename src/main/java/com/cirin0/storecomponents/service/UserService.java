package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.dto.UserResponseDTO;
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

  private final UserRepository userRepository;

  private BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return Optional.ofNullable(userRepository.findById(id).orElseThrow(
        () -> new RuntimeException("User not found with id " + id)));
  }

  public UserResponseDTO register(UserDTO userDTO) {
    User user = new User();
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    user.setPassword(encoder().encode(userDTO.getPassword()));
    user.setRole(userDTO.getRole());
    User savedUser = userRepository.save(user);

    UserResponseDTO responseDTO = new UserResponseDTO();
    responseDTO.setFirstName(savedUser.getFirstName());
    responseDTO.setLastName(savedUser.getLastName());
    responseDTO.setEmail(savedUser.getEmail());
    responseDTO.setRole(String.valueOf(savedUser.getRole()));
    return responseDTO;
  }

  public Optional<UserResponseDTO> login(UserRequestDTO userRequestDTO) {
    Optional<User> user = userRepository.findByEmail(userRequestDTO.getEmail());
    if (user.isPresent() && encoder().matches(userRequestDTO.getPassword(), user.get().getPassword())) {
      User foundUser = user.get();
      UserResponseDTO responseDTO = new UserResponseDTO();
      responseDTO.setFirstName(foundUser.getFirstName());
      responseDTO.setLastName(foundUser.getLastName());
      responseDTO.setEmail(foundUser.getEmail());
      responseDTO.setRole(String.valueOf(foundUser.getRole()));
      return Optional.of(responseDTO);
    }
    return Optional.empty();
  }


  public User updateUser(Long id, UserDTO userDTO) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
      user.setPassword(encoder().encode(userDTO.getPassword()));
    }
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
