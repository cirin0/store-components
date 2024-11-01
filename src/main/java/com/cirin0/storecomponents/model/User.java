package com.cirin0.storecomponents.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;
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
  @Column(nullable = false) //unique = true,
  private String email;

  @Getter
  @Column(nullable = false)
  @JsonIgnore
  private String password;

  private LocalDateTime createdAt = LocalDateTime.now();

  @Enumerated(value = EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Order> orders;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private Cart cart;
}
