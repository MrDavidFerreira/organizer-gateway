import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.11"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion // Open classes for custom annotations
    kotlin("plugin.spring") version kotlinVersion // Open classes for Spring annotations

    id("org.springframework.boot") version "2.1.1.RELEASE" //Spring Boot support
    // Control the project's dependencies versions like Maven's BOM
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

group = "com.davfer.organizer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone") //for spring cloud dependencies
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect")) // Kotlin reflection library (mandatory as of Spring Framework 5)

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit") //Exclude JUnit 4
    }

    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:Greenwich.RC2")) //BOM
    implementation("org.springframework.cloud:spring-cloud-starter-config") //in order to connect to the Config Server
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")//Allow Kotlin null-safety with Spring
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
}