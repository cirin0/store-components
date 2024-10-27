package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRegister;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody UserRegister userRegister) {
    UserDTO registeredUser = userService.createUser(userRegister);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<UserDTO> login(@RequestBody UserRegister userRegister) {
    UserDTO loginUser = userService.loginUser(userRegister);
    return ResponseEntity.ok(loginUser);
  }
}