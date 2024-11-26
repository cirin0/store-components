package com.cirin0.storecomponents.controller.web;

import com.cirin0.storecomponents.dto.category.CategoryDTO;
import com.cirin0.storecomponents.dto.product.ProductDTO;
import com.cirin0.storecomponents.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminWebController {
  private final CategoryService categoryService;
  private final ProductService productService;
  private final OrderService orderService;
  private final UserService userService;
  private final CartService cartService;

  @GetMapping
  public String adminDashboard() {
    return "admin/dashboard";
  }

  @GetMapping("/categories")
  public String getAllCategories(Model model) {
    model.addAttribute("pageTitle", "Категорії");
    model.addAttribute("categories", categoryService.getAllCategories());
    return "admin/admin-category-list";
  }

  @GetMapping("/categories/add")
  public String showAddCategoryPage(Model model) {
    model.addAttribute("pageTitle", "Додати категорію");
    model.addAttribute("category", new CategoryDTO());
    return "category/create-category";
  }

  @PostMapping("/categories/add")
  public String addCategory(@ModelAttribute CategoryDTO categoryDTO) {
    categoryService.createCategory(categoryDTO);
    return "redirect:/admin/categories";
  }

  @GetMapping("/categories/{id}/edit")
  public String editCategory(@PathVariable Long id, Model model) {
    model.addAttribute("pageTitle", "Редагування категорії");
    model.addAttribute("category", categoryService.getCategoryById(id));
    return "category/edit-category";
  }

  @PostMapping("/categories/{id}/edit")
  public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryDTO categoryDTO) {
    categoryService.updateCategory(id, categoryDTO);
    return "redirect:/admin/categories";
  }

  @GetMapping("/categories/{id}/delete")
  public String deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return "redirect:/admin/categories";
  }

  // Product

  @GetMapping("/products")
  public String getAllProducts(Model model) {
    model.addAttribute("pageTitle", "Товари");
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("categories", categoryService.getAllCategories());
    return "admin/admin-product-list";
  }

  @GetMapping("/products/add")
  public String showAddProductPage(Model model) {
    model.addAttribute("pageTitle", "Додати товар");
    model.addAttribute("product", new ProductDTO());
    model.addAttribute("categories", categoryService.getAllCategories());
    return "product/create-product";
  }

  @PostMapping("/products/add")
  public String addProduct(@ModelAttribute ProductDTO productDTO) {
    productService.createProduct(productDTO);
    return "redirect:/admin/products";
  }

  @GetMapping("/products/{id}/edit")
  public String editProduct(@PathVariable Long id, Model model) {
    model.addAttribute("product", productService.getProductById(id));
    model.addAttribute("categories", categoryService.getAllCategories());
    return "product/edit-product";
  }

  @PostMapping("/products/{id}/edit")
  public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO) {
    productService.updateProduct(id, productDTO);
    return "redirect:/admin/products";
  }

  @GetMapping("/products/{id}/delete")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/admin/products";
  }

  // Order

  @GetMapping("/orders")
  public String getAllOrders(Model model) {
    model.addAttribute("pageTitle", "Замовлення");
    model.addAttribute("orders", orderService.getAllOrders());
    return "admin/admin-order-list";
  }

  // Cart

  @GetMapping("/users/cart/{userId}")
  public String getUserCart(@PathVariable Long userId, Model model) {
    model.addAttribute("pageTitle", "Кошик користувача");
    model.addAttribute("cart", cartService.getCartByUserId(userId));
    return "admin/admin-user-cart";
  }

}
