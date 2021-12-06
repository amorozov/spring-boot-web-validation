import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.openapi.generator") version "5.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

val generatedProjectDir = "${buildDir}/generated-sources/example"

val generateTask = tasks.named<GenerateTask>("openApiGenerate") {

    generatorName.set("spring")
    outputDir.set(generatedProjectDir)
    inputSpec.set("${projectDir}/src/main/openapi/sample.yaml")
    configFile.set("${projectDir}/sample-spring-reactive.yaml")
    inputs.file("${projectDir}/sample-spring-reactive.yaml")
    doFirst {
        delete(generatedProjectDir)
    }
}
