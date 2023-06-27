import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.gradle.jvm.tasks.Jar

plugins {
    val kotlinVersion = "1.8.22"
    id("org.springframework.boot") version "3.1.0"
    id("org.openapi.generator") version "6.6.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    implementation("org.springframework.boot:spring-boot-starter-webflux") {
        exclude(group = "ch.qos.logback")
    }
    implementation("org.springframework.boot:spring-boot-starter-actuator") {
        exclude(group = "ch.qos.logback")
    }
    implementation("io.klogging:klogging-spring-boot-starter:0.3.3")

    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.apache.tomcat.embed:tomcat-embed-el")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.12")
    implementation("io.swagger.core.v3:swagger-models:2.2.12")

    runtimeOnly("org.springdoc:springdoc-openapi-starter-webflux-ui:2.1.0")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.1")

}

val generatedProjectDir = "${buildDir}/generated-sources/sample"

val generateTask = tasks.named<GenerateTask>("openApiGenerate") {

    generatorName.set("kotlin-spring")
    outputDir.set(generatedProjectDir)
    inputSpec.set("${rootDir}/src/main/openapi/sample.yaml")
    configFile.set("${projectDir}/spring-config.yaml")
    inputs.file("${projectDir}/spring-config.yaml")
    doFirst {
        delete(generatedProjectDir)
    }
    doLast {
        delete("${generatedProjectDir}/src/main/kotlin/org/sample/webflux/api/ApiUtil.kt")
        delete("${generatedProjectDir}/src/main/resources/application.properties")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

sourceSets {
    main {
        kotlin {
            srcDirs("${generatedProjectDir}/src/main/kotlin")
        }
        resources {
            srcDirs("${generatedProjectDir}/src/main/resources")
        }
    }
}

tasks.named<Jar>("kotlinSourcesJar") {
    dependsOn(generateTask)
}

tasks.named<KotlinCompile>("compileKotlin") {
    dependsOn(generateTask)
}



tasks.named<ProcessResources>("processResources") {
    dependsOn(generateTask)
}
