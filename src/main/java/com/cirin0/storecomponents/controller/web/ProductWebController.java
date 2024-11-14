package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.AddToCartDTO;
import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.dto.user.UserDetailsDTO;
import com.cirin0.storecomponents.service.CartService;
import com.cirin0.storecomponents.service.CategoryService;
import com.cirin0.storecomponents.service.ProductService;
import com.cirin0.storecomponents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductWebController {

  private final ProductService productService;
  private final CategoryService categoryService;
  private final UserService userService;
  private final CartService cartService;

  @GetMapping
  public String getAllProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("pageTitle", "Список товарів");
    return "product/product-list";
  }

  @GetMapping("/{id}")
  public String getProductDetails(@PathVariable Long id, Model model) {
    model.addAttribute("product", productService.getProductById(id));
    model.addAttribute("pageTitle", productService.getProductById(id).getName());
    return "product/product-details";
  }

  @GetMapping("/new-product")
  public String showAddProductPage(Model model) {
    ProductDTO productDTO = new ProductDTO();
    model.addAttribute("pageTitle", "Додати товар");
    model.addAttribute("product", productDTO);
    model.addAttribute("categories", categoryService.getAllCategories());
    return "product/create-product";
  }

  @PostMapping("/new-product")
  public String addProduct(ProductDTO productDTO) {
    productService.createProduct(productDTO);
    return "redirect:/products";
  }

  @PostMapping("cart/add-product")
  public String addProductToCart(Principal authentication, AddToCartDTO addToCartDTO, Model model) {
    UserDetailsDTO user = userService.getUserByEmail(authentication.getName());
    cartService.addToCart(user.getId(), addToCartDTO);
    model.addAttribute("message", "Товар додано до кошика");
    return "redirect:/products";
  }
}
