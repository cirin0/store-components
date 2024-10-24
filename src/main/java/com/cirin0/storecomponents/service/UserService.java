package com.cirin0.storecomponents.service;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.dto.UserResponseDTO;
import com.cirin0.storecomponents.mapper.UserMapper;
import com.cirin0.storecomponents.mapper.UserMapperTest;
import com.cirin0.storecomponents.model.ERole;
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
  private final UserMapperTest userMapperTest;

  private BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapperTest::toDTO)
        .toList();
  }

  public UserDTO getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new RuntimeException("User not found with id " + id);
    }
    return userMapperTest.toDTO(user.get());
  }

  public UserDTO createUser(UserRequestDTO userRequestDTO) {
    User user = userMapperTest.toEntity(userRequestDTO);
    user.setPassword(encoder().encode(userRequestDTO.getPassword()));
    User savedUser = userRepository.save(user);
    return userMapperTest.toDTO(savedUser);
  }

  public UserResponseDTO signIn(UserRequestDTO userRequestDTO) {
    User user = userRepository.findByEmail(userRequestDTO.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found with email " + userRequestDTO.getEmail()));
    if (!encoder().matches(userRequestDTO.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }
    return userMapper.userDTOToUserResponseDTO(userMapper.userToUserDTO(user));
  }


  public UserDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty()) {
      throw new RuntimeException("User not found with id " + id);
    }
    User user = userOptional.get();
    user.setEmail(userRequestDTO.getEmail());
    user.setPassword(encoder().encode(userRequestDTO.getPassword()));
    user.setRole(userRequestDTO.getRole() == null ? ERole.USER : userRequestDTO.getRole());
    User updatedUser = userRepository.save(user);
    return userMapperTest.toDTO(updatedUser);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id " + id);
    }
    userRepository.deleteById(id);
  }
}
