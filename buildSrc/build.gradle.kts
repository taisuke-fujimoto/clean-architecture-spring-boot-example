plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.allopen)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.ktlint.gradle)
    implementation(libs.spring.boot.gradle.plugin)
    implementation(libs.gradle.docker.compose.plugin)
}
