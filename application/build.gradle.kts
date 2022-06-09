description = "application module"

plugins {
    id("org.jetbrains.kotlin.plugin.allopen")
    id("org.asciidoctor.convert") version "1.5.9.2"
    kotlin("plugin.jpa") version "1.4.32"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor")
    runtimeOnly("com.h2database:h2:1.4.200")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
}