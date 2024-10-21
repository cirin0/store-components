package com.cirin0.storecomponents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile({"prod", "default", "dev"})
public class StoreComponentsApplication {
  public static void main(String[] args) {
    SpringApplication.run(StoreComponentsApplication.class, args);
  }

}