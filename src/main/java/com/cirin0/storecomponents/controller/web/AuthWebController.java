package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.UserDTO;
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
  public String showLoginPage(Model model) {
//    UserRequestDTO userRequestDTO = new UserRequestDTO();
    model.addAttribute("pageTitle", "Увійти");
//    model.addAttribute("user", userRequestDTO);
    return "login";
  }

//  @PostMapping("/login")
//  public String loginUser(@ModelAttribute("user") @Valid UserRequestDTO userRequestDTO) {
//    userService.login(userRequestDTO);
//    return "redirect:/";
//  }

  @GetMapping("/registration")
  public String showRegistrationPage(Model model) {
    UserDTO userDto = new UserDTO();
    model.addAttribute("pageTitle", "Зареєструватися");
    model.addAttribute("user", userDto);
    return "register";
  }

  @PostMapping("/registration")
  public String registerUser(@ModelAttribute("user") @Valid UserDTO userDto, WebRequest request) {
    userService.register(userDto);
    return "redirect:/auth/login";
  }
}
