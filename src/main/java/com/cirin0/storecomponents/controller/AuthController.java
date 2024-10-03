package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.service.UserService;
import com.cirin0.storecomponents.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public String registerUser(@RequestBody User user) {
    userService.createUser(user);
    return "User registered";
  }
}