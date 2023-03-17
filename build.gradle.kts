plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"

    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // TODO : remove
    implementation("org.codehaus.jettison:jettison:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")
//    implementation("io.arrow-kt:arrow-optics:1.1.5")
//    implementation("io.arrow-kt:arrow-optics-ksp-plugin:1.1.5")
    testImplementation("io.kotest:kotest-common:5.5.5")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("io.kotest:kotest-assertions-json:5.5.5")
    implementation("com.squareup.okio:okio:3.3.0")
    ksp("at.kopyk:kopykat-ksp:1.0.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)

    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

ksp {
    arg("generate", "packages:person")
//    arg("mutableCopy", "false")
//    arg("copyMap", "true")
//    arg("hierarchyCopy", "false")
}

application {
    mainClass.set("MainKt")
}