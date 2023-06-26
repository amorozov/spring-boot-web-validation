package org.sample.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class

)
@ComponentScan(
        basePackages = {"org.sample.webmvc", "org.sample.webmvc.api" , "org.sample.webmvc.configuration"},
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class WebMvcValidationApp {

    public static void main(String[] args) {
        new SpringApplication(WebMvcValidationApp.class).run(args);
    }
}
