plugins {
    id("my.base")
}

dependencies {
    implementation(project(":entities:task-entity"))

    implementation("org.springframework:spring-context")
}
