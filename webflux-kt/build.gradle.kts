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

tasks {
    val generatedProjectDir = "${buildDir}/generated-sources/service"
    val openApiYaml = "${rootDir}/src/main/openapi/sample.yaml"

    val yamlGenerateTask = create<GenerateTask>("openApiYamlGenerate") {
        val generatorConfig = "${projectDir}/yaml-config.yaml"

        generatorName.set("openapi-yaml")
        outputDir.set(generatedProjectDir)
        doFirst {
            delete(generatedProjectDir)
        }

        inputSpec.set(openApiYaml)
        configFile.set(generatorConfig)
        inputs.file(generatorConfig)
    }

    val codeGenerateTask = named<GenerateTask>("openApiGenerate") {
        dependsOn(yamlGenerateTask)

        val generatorConfig = "${projectDir}/code-config.yaml"
        generatorName.set("kotlin-spring")
        outputDir.set(generatedProjectDir)
        inputSpec.set(openApiYaml)
        configFile.set(generatorConfig)
        inputs.file(generatorConfig)

        doLast {
            delete("${generatedProjectDir}/src/main/kotlin/org/sample/webflux/api/ApiUtil.kt")
        }
    }

    withType<KotlinCompile> {
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

    named<Jar>("kotlinSourcesJar") {
        dependsOn(codeGenerateTask)
    }

    named<KotlinCompile>("compileKotlin") {
        dependsOn(codeGenerateTask)
    }

    named<ProcessResources>("processResources") {
        dependsOn(codeGenerateTask)
    }

}
