package com.anacarolinaebruno.wedding.api.config;

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
    public OpenAPI defineOpenApi() {
        Server serverDev = new Server();
        serverDev.setUrl("http://localhost:8080");
        serverDev.setDescription("Development");

        Server serverProd = new Server();
        serverProd.setUrl("https://api.anacarolina-e-bruno.com.br");
        serverProd.setDescription("Production");

        Contact myContact = new Contact();
        myContact.setName("Bruno Rodrigues");
        myContact.setEmail("contact@brunodsr.com");

        Info information = new Info()
                .title("Wedding website store and RSVP API")
                .version("1.0")
                .description("This API exposes endpoint to create a store of gifts exposing checkout endpoint with Mercadopago and a RSVP system.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(serverDev, serverProd));
    }
}
