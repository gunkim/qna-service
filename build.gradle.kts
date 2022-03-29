buildscript {
    val version_kotlin by extra { "1.6.10" }
    val version_springBoot by extra { "2.6.5" }

    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://repo.spring.io/plugins-release")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$version_kotlin")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$version_springBoot")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$version_kotlin")
    }
}

plugins {
    java
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
}
allprojects {
    group = "io.github.gunkim"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}