package my

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jlleitschuh.gradle.ktlint")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    compilerOptions {
        allWarningsAsErrors.set(true)
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

ktlint {
    version.set("1.5.0")
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
}
