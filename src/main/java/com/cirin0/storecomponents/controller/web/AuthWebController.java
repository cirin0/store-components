package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.repository.UserRepository;
import com.cirin0.storecomponents.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthWebController {

  private final UserService userService;
  private final UserRepository userRepository;

  @GetMapping("/login")
  public String loginPage(Model model) {
    model.addAttribute("pageTitle", "Увійти");
    return "user/login";
  }

  @GetMapping("/register")
  public String registerPage(Model model) {
    UserRegister userRegister = new UserRegister();
    model.addAttribute("user", userRegister);
    model.addAttribute("pageTitle", "Зареєструватися");
    return "user/register";
  }

  @PostMapping("/register")
  public String registerUser(Model model, @ModelAttribute UserRegister userRegister, BindingResult result) {
    if (userRepository.existsByEmail(userRegister.getEmail())) {
      model.addAttribute("emailExists", "Користувач з такою поштою вже існує");
      model.addAttribute("user", userRegister);
      model.addAttribute("pageTitle", "Зареєструватися");
      return "user/register";
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userRegister);
      model.addAttribute("pageTitle", "Зареєструватися");
      return "user/register";
    }
    try {
      userService.registerUser(userRegister);
      return "redirect:/auth/login";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("user", userRegister);
      model.addAttribute("pageTitle", "Зареєструватися");
      return "user/register";
    }
  }
}