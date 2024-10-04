package com.cirin0.storecomponents.controller;

import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  // Пофіксити метод
  @GetMapping("/{userId}")
  public User showUser(@PathVariable Long userId) {
    return userService.getUserById(userId);
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
