package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.service.OrderService;
import com.cirin0.storecomponents.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderWebController {

  private final OrderService orderService;
  private final UserService userService;

  @GetMapping
  public String showOrders(Principal authentication, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    model.addAttribute("orders", orderService.getOrdersByUserId(user.getId()));
    model.addAttribute("pageTitle", "Замовлення");
    return "orders";
  }

  @PostMapping("/create-order")
  public String createOrder(Principal authentication) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    orderService.createOrder(user.getId());
    return "redirect:/orders";
  }

  @GetMapping("/{orderId}")
  public String showOrder(Model model, @PathVariable Long orderId) {
    model.addAttribute("order", orderService.getOrderById(orderId));
    model.addAttribute("pageTitle", "Замовлення");
    return "order-details";
  }

//  @GetMapping("/user-orders/{userId}")
//  public String showUserOrders(Model model, @PathVariable String userId) {
//    model.addAttribute("pageTitle", "Замовлення користувача");
//    return "user-orders";
//  }
}
