package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserWebController {

  private final UserService userService;

  @GetMapping("/profile")
  public String profilePage(Model model, Principal authentication) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    model.addAttribute("user", user);
    return "user/profile";
  }

  @GetMapping("/edit")
  public String editProfilePage(Model model, Principal authentication) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    model.addAttribute("user", user);
    return "user/edit-profile";
  }

  @PostMapping("/edit")
  public String editProfile(UserUpdate userUpdate, Principal authentication) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    userService.updateUser(user.getId(), userUpdate);
    return "redirect:/user/profile";
  }

  @GetMapping("/change-role-admin")
  public String changeRoleAdminPage(Principal authentication, Model model) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    model.addAttribute("user", user);
    return "user/change-role-admin";
  }

  @PostMapping("/change-role-admin")
  public String changeRoleAdmin(Principal authentication) {
    if (authentication == null) {
      return "redirect:/auth/login";
    }
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    userService.changeRoleAdmin(user.getId());
    return "redirect:/user/profile";
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
