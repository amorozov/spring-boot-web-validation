package org.sample.webmvc.configuration;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonNullableConfiguration {
    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
