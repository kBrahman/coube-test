plugins {
    id("org.springframework.boot") version "4.0.5" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
    group = "kz.coube"
    version = "1.0.2"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:6.0.0"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.register<Copy>("buildRelease") {
    group = "build"
    description = "Copies the bootJar from the app module to the root release folder."
    dependsOn(":app:bootJar")
    from(project(":app").layout.buildDirectory.file("libs/app-${project.version}.jar"))
    into(layout.projectDirectory.dir("release"))
    rename { "coube-delivery-${project.version}.jar" }
}