plugins {
    id("my.base")
}

dependencies {
    implementation(project(":entities:task-entity"))
    implementation(project(":use-cases:task-view-use-case-query"))

    implementation("org.springframework:spring-context")
}
