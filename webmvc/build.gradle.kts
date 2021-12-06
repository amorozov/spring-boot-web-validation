plugins {
    java
    id("org.springframework.boot") version "2.6.1"
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.apache.tomcat.embed:tomcat-embed-el")
    implementation("io.swagger:swagger-annotations:1.5.14")
    compileOnly("io.springfox:springfox-core:3.0.0")
    runtimeOnly("org.webjars:webjars-locator-core")
    runtimeOnly("org.webjars:swagger-ui:4.1.2")
}