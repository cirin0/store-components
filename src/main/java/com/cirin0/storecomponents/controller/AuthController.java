package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.service.UserService;
import com.cirin0.storecomponents.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @GetMapping("/register-form")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }
  
  @PostMapping("/register")
  public String registerUser(@ModelAttribute User user) {
    userService.createUser(user);
    return "redirect:/login";
  }

  @GetMapping("/login-form")
  public String showLoginForm() {
    return "redirect:/login";
  }
}