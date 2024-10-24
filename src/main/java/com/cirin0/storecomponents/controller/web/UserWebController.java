package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserWebController {

  private final UserService userService;


  @GetMapping("/{id}")
  public String getUserById(@PathVariable Long id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "user-page";
  }
}
