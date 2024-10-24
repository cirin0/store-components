package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRequestDTO;
import com.cirin0.storecomponents.dto.UserResponseDTO;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
    UserDTO userDTO = userService.createUser(userRequestDTO);
    return ResponseEntity.ok(userDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO userDTO = userService.signIn(userRequestDTO);
    return ResponseEntity.ok(userDTO);
  }
}