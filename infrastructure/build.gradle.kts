import org.springframework.boot.gradle.tasks.bundling.BootJar

description = "infrastructure module"

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
}