package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.service.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  /*
  @GetMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password) {
    return userService.login(username, password);
  }
  */
  /*
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    return userService.register(user);
  }
   */
  @GetMapping("/register")
  public String register() {
   return "User registered";
  }

  @GetMapping("/login")
  public String login() {
    return "User logged in";
  }
}