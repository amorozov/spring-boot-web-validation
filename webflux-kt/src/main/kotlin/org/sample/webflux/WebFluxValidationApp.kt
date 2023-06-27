package org.sample.webflux

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class
)
@ComponentScan(
    basePackages = ["org.sample.webflux", "org.sample.webflux.api", "org.sample.webflux.model"],
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class
)
class WebFluxValidationApp

fun main(args: Array<String>) {
    runApplication<WebFluxValidationApp>(*args)
}
