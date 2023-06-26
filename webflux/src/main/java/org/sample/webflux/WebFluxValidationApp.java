package org.sample.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
        basePackages = {"org.sample.webflux", "org.sample.webflux.api" , "org.sample.webflux.configuration"},
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class WebFluxValidationApp {

    public static void main(String[] args) {
        new SpringApplication(WebFluxValidationApp.class).run(args);
    }
}
