import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.jpa") version "1.6.0-RC"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("kapt") version "1.4.32"
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
}

dependencies {
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    runtimeOnly("org.postgresql:postgresql:42.2.24.jre7")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")

    //OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:2.5.5")
    //Spring security
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
    //Spring web stater
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    //json module for kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    //Kotlin stuff
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    //Spring security dsl for kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
    testImplementation("io.projectreactor:reactor-test:3.4.10")
    testImplementation("org.springframework.security:spring-security-test:5.5.1")

    //MapStruct dependency
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
}

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

tasks.withType<Test> {
    useJUnitPlatform()
}
