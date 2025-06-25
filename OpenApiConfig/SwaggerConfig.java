package com.example.PerfulandiaSPA.OpenApiConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI PerfulandiaSPAOpenApi(){
        return new OpenAPI()
            .info(new Info()
                .title("API - Perfulandia SPA")
                .description("Documentacion de las API REST para la gestion de los microservicios de catalogo de perfumes, usuarios y carrito de compras")
                .version("v2.0"));
    }
}
