package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.service.UserService;
import com.cirin0.storecomponents.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register-form")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }
/*
  @PostMapping("/register")
  public String registerUser(@ModelAttribute User user) {
    user.setRole("ROLE_USER");
    userService.save(user);
    return "redirect:/login";
  }
*/
  @GetMapping("/login-form")
  public String showLoginForm() {
    return "redirect:/login";
  }
}