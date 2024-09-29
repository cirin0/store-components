package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  @Autowired
  private UserService userService;

  @GetMapping("/auth/registerU")
  public String showRegisterForm(Model model) {
    model.addAttribute("message", "Please register to access the store");
    return "register";
  }

  @GetMapping("/auth/loginU")
  public String showLoginForm(Model model) {
    model.addAttribute("message", "Please log in to access the store");
    return "redirect:/login";
  }

  @GetMapping("/register")
  public ResponseEntity<User> register(@RequestParam User user) {
    return ResponseEntity.ok(userService.registerUser(user));
  }

  @GetMapping("/login")
  public ResponseEntity<User> login(@RequestParam User user) {
    return ResponseEntity.ok(userService.findByUsername(user.getUsername()).orElseThrow());
  }
}
