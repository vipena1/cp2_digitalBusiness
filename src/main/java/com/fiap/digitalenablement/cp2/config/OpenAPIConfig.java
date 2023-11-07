package com.fiap.digitalenablement.cp2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI gerarDocumentacao() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.description("URL de desenvolvimento local.");

        Contact contact = new Contact();
        contact.setEmail("viniciuspena81@gmail.com");
        contact.setName("Vinicius");
        Info  info = new Info().title("CP2_Digital_Business")
                .version("0.1")
                .contact(contact)
                .description("Documentação de API da CP2 de Digital Business.");
        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
