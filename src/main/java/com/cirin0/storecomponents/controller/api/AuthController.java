package com.cirin0.storecomponents.controller.api;

import com.cirin0.storecomponents.config.LoginResponse;
import com.cirin0.storecomponents.dto.UserDTO;
import com.cirin0.storecomponents.dto.UserRegister;
import com.cirin0.storecomponents.jwt.JwtService;
import com.cirin0.storecomponents.jwt.LoginUserDto;
import com.cirin0.storecomponents.jwt.RegisterUserDto;
import com.cirin0.storecomponents.model.User;
import com.cirin0.storecomponents.service.AuthenticationService;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtService jwtService;
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authenticationService.login(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }
}