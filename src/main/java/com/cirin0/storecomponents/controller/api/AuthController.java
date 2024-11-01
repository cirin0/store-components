package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  @PostMapping("/register")
  public ResponseEntity<User> register() {
    return null;
  }

  @PostMapping("/login")
  public ResponseEntity<User> authenticate() {
    return null;
  }
}