plugins {
    id("my.base")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":entities:task-entity"))
    implementation(project(":gateways:postgresql-gateway"))
    implementation(project(":use-cases:task-view-use-case"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}
