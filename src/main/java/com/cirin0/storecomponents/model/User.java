package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  @Email
  private String email;
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Cart> carts;

  public User() {
  }

  public User(String username, String email, String encodedPassword) {
    this.username = username;
    this.email = email;
    this.password = encodedPassword;
  }

  //@ManyToOne(fetch = FetchType.EAGER)
  //@JoinTable(name = "user_role",
  //        joinColumns = @JoinColumn(name = "user_id"),
  //        inverseJoinColumns = @JoinColumn(name = "role_id"))
  //private Set<Role> roles = new HashSet<>();
}
