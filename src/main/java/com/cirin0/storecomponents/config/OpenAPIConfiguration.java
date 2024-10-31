package com.cirin0.storecomponents.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

  @Bean
  public OpenAPI defineOpenAPI() {
    Server server = new Server();
    server.setUrl("http://localhost:5000");
    server.setDescription("Development");

    Contact myContact = new Contact();
    myContact.setName("Cirin0");

    Info information = new Info()
        .title("Store API")
        .version("1.0.0")
        .description("API for a store")
        .contact(myContact);
    return new OpenAPI()
        .info(information)
        .servers(List.of(server));
  }
}
