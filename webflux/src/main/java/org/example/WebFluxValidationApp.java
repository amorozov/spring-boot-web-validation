package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
public class WebFluxValidationApp {

    public static void main(String[] args) {
        new SpringApplication(WebFluxValidationApp.class).run(args);
    }
}
