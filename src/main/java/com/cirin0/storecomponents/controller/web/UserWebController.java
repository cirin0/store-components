package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserWebController {

  private final UserService userService;

  @GetMapping("/profile")
  public String profilePage(Model model, Principal authentication) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    String email = authentication.getName();
    UserDetailsDTO user = userService.getUserByEmail(email);
    model.addAttribute("user", user);
    return "user/profile";
  }
//  @PostMapping("/profile.html")
//  public String updateProfile(@AuthenticationPrincipal UserDetails userDetails, UserDTO userDTO) {
//    userService.updateUser(userDetails.getUsername(), userDTO);
//    return "redirect:/users/profile.html";
//  }


//  @GetMapping("/{id}")
//  public String getUserById(@PathVariable Long id, Model model) {
//    model.addAttribute("user", userService.getUserById(id));
//    return "user-page";
//  }
}
