package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserRegister;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserWebController {

  private final UserService userService;

  @GetMapping("/profile")
  public String profilePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    UserRegister user = userService.getUserByEmail(userDetails.getUsername());
    model.addAttribute("user", user);
    return "profile";
  }
//  @PostMapping("/profile")
//  public String updateProfile(@AuthenticationPrincipal UserDetails userDetails, UserDTO userDTO) {
//    userService.updateUser(userDetails.getUsername(), userDTO);
//    return "redirect:/users/profile";
//  }


//  @GetMapping("/{id}")
//  public String getUserById(@PathVariable Long id, Model model) {
//    model.addAttribute("user", userService.getUserById(id));
//    return "user-page";
//  }
}
