package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public String showNotLoggedInMessage() {
    return "Please log in to access your user information";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin/all")
  public String showUsers() {
    return userService.getUsers().toString();
  }

  @GetMapping("/{userId}")
  public String showUser(@PathVariable Long userId) {
    if (userId == null) {
      return "User not found";
    }
    return userService.getUserById(userId).toString();
  }

  @PutMapping("/{userId}")
  public String updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
    userService.updateUser(userId, updatedUser);
    return "User updated";
  }

  @DeleteMapping("/{userId}")
  public String deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return "User deleted";
  }
}
