package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
  @Autowired
  private UserService userService;

  @GetMapping("/auth/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(User user) {
    userService.registerUser(user);
    return "redirect:/login"; // Перенаправлення на сторінку входу
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login"; // Повертаємо сторінку входу
  }

  @PostMapping("/auth/login")
  public String loginUser() {
    return "redirect:/"; // Перенаправлення на головну сторінку
  }
}
