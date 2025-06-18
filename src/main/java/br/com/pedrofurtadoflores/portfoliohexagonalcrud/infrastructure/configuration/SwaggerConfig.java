package br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Portfolio Hexagonal CRUD API")
                    .description("RESTful API using Hexagonal Architecture")
                    .version("1.0")
                );
    }
}
