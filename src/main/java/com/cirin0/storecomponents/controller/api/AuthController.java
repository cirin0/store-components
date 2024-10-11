package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.dto.UserResponseDTO;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
    UserResponseDTO responseDTO = userService.register(userDTO);
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO) {
    Optional<UserResponseDTO> responseDTO = userService.login(userRequestDTO);
    if (responseDTO.isPresent()) {
      return ResponseEntity.ok(responseDTO.get());
    }
    return ResponseEntity.status(401).body("Invalid credentials");
  }
}