package com.cirin0.storecomponents.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String homePage(Model model) {
    model.addAttribute("pageTitle", "Інтернет-магазин");
    return "home";
  }
}
