plugins {
    id("java")
    id("application")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

application {
    mainClass.set("kz.coube.App")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    runtimeOnly(project(":delivery"))
    runtimeOnly(project(":admin"))
    runtimeOnly("com.h2database:h2")
}


