package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderWebController {

  private final OrderService orderService;

  @GetMapping
  public String showOrders(Model model) {
    model.addAttribute("pageTitle", "Замовлення");
    return "orders";
  }
  
  @GetMapping("/user-orders/{userId}")
  public String showUserOrders(Model model, @PathVariable String userId) {
    model.addAttribute("pageTitle", "Замовлення користувача");
    return "user-orders";
  }
}
