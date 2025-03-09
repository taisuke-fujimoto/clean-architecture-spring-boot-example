plugins {
    id("my.base")
}

dependencies {
    implementation(project(":entities:task-entity"))
    implementation(project(":use-cases:task-view-use-case-query"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
}
