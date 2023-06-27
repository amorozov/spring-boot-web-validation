package org.sample.webflux.configuration

import com.fasterxml.jackson.databind.Module
import org.openapitools.jackson.nullable.JsonNullableModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonNullableConfiguration {
    @Bean
    fun jsonNullableModule(): Module {
        return JsonNullableModule()
    }
}
