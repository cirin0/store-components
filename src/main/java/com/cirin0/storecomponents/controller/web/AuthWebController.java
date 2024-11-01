package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.UserRegister;
import com.cirin0.storecomponents.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthWebController {

  private final UserService userService;

  @GetMapping("/login")
  public String loginPage(Model model) {
    UserRegister userRegister = new UserRegister();
    model.addAttribute("pageTitle", "Увійти");
    model.addAttribute("user", userRegister);
    return "login";
  }

  @PostMapping("/login")
  public String loginUser(@ModelAttribute("user") @Valid UserRegister userRegister) {
    userService.loginUser(userRegister);
    return "redirect:/";
  }

  @GetMapping("/register")
  public String registerPage(Model model) {
    UserRegister userRegister = new UserRegister();
    model.addAttribute("pageTitle", "Зареєструватися");
    model.addAttribute("user", userRegister);
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute("user") @Valid UserRegister userRegister, WebRequest request) {
    userService.createUser(userRegister);
    return "redirect:/auth/login";
  }
}
