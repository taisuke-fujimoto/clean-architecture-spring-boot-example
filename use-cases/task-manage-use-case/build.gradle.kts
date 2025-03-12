plugins {
    id("my.base")
}

dependencies {
    implementation(project(":entities:task-entity"))
    implementation(project(":entities:task-entity-reader"))
    implementation(project(":entities:task-entity-writer"))

    implementation("org.springframework:spring-context")
}
