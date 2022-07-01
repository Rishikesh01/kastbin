import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id ("java")
    id("application")
    id("org.jetbrains.kotlin.plugin.noarg") version "1.7.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.0"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    id("org.springframework.experimental.aot") version "0.12.0"
    kotlin("plugin.jpa") version "1.7.0"
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.7.0"
    kotlin("kapt") version "1.7.0"
}

group = "com.kastbin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/release") }
}

dependencies {
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    runtimeOnly("org.postgresql:postgresql:42.3.6")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.0")
    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:2.7.0")
    //Spring security
    implementation("org.springframework.boot:spring-boot-starter-security:2.7.0")
    //Spring web stater
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
    //json module for kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    //Kotlin stuff
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.6")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    //Spring security dsl for kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.2")
    // developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0")
    testImplementation("io.projectreactor:reactor-test:3.4.18")
    testImplementation("org.springframework.security:spring-security-test:5.7.1")

    //MapStruct dependency
    implementation("org.mapstruct:mapstruct:1.5.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.1.Final")
}

//project.setProperty("javaLauncher", "com.kastbin.KastbinApplicationKt")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "KastbinApplicationKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf(
        "BP_NATIVE_IMAGE" to "true"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}
