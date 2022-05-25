description = "application module"

plugins {
    id("org.jetbrains.kotlin.plugin.allopen")
    kotlin("plugin.jpa") version "1.4.32"
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2:1.4.200")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
}