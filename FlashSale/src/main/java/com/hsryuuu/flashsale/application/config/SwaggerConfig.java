package com.hsryuuu.flashsale.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI flashSaleOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flash Sale API")
                        .version("v1")
                        .description("Flash Sale API Documentation"));
    }
}