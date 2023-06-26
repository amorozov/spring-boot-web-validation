import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    id("org.springframework.boot") version "3.1.0"
    id("org.openapi.generator") version "6.6.0"
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.apache.tomcat.embed:tomcat-embed-el")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.12")
    implementation("io.swagger.core.v3:swagger-models:2.2.12")

    runtimeOnly("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
}

val generatedProjectDir = "${buildDir}/generated-sources/sample"

val generateTask = tasks.named<GenerateTask>("openApiGenerate") {

    generatorName.set("spring")
    outputDir.set(generatedProjectDir)
    inputSpec.set("${rootDir}/src/main/openapi/sample.yaml")
    configFile.set("${projectDir}/spring-config.yaml")
    inputs.file("${projectDir}/spring-config.yaml")
    doFirst {
        delete(generatedProjectDir)
    }
    doLast {
        delete("${generatedProjectDir}/src/main/java/org/sample/webmvc/api/TestApiController.java")
        delete("${generatedProjectDir}/src/main/java/org/sample/webmvc/api/ApiUtil.java")
        delete("${generatedProjectDir}/src/main/java/org/sample/webmvc/OpenApiGeneratorApplication.java")
        delete("${generatedProjectDir}/src/main/java/org/sample/webmvc/RFC3339DateFormat.java")
        delete("${generatedProjectDir}/src/main/resources/application.properties")
    }
}

java {
    withSourcesJar()
}

sourceSets {
    main {
        java {
            srcDirs("${generatedProjectDir}/src/main/java")
        }
        resources {
            srcDirs("${generatedProjectDir}/src/main/resources")
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn(generateTask)
}

tasks.named<Jar>("sourcesJar") {
    dependsOn(generateTask)
}

tasks.named<ProcessResources>("processResources") {
    dependsOn(generateTask)
}
