package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.dto.user.UserDTO;
import com.cirin0.storecomponents.dto.user.UserUpdate;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<UserDTO> userDTOList = userService.getAllUsers();
    return ResponseEntity.ok(userDTOList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    UserDTO userDTO = userService.getUserById(id);
    return ResponseEntity.ok(userDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserUpdate> updateUser(@PathVariable Long id, @RequestBody UserUpdate userUpdate) {
    UserUpdate updatedUser = userService.updateUser(id, userUpdate);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
