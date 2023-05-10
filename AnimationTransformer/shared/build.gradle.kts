plugins {
    kotlin("multiplatform") version "1.8.0"
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.0"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
    id("io.kotest.multiplatform") version "5.0.2"
}

kotlin {
    jvmToolchain(8)
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        nodejs()
        binaries.library()
        binaries.executable()
        browser {
            webpackTask {
                outputFileName = "kmp_lib.js"
                output.library = "kmpLib"
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val okioVersion = "3.3.0"
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")
                //ksp("at.kopyk:kopykat-ksp:1.0.1")
                //implementation("com.google.devtools.ksp:symbol-processing-api:")
                //implementation("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.9")
                implementation("com.squareup.okio:okio:$okioVersion")
            }
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                //implementation("io.kotest:kotest-common:5.5.5")
                //implementation("io.kotest:kotest-runner-junit5:5.5.5")
                //implementation("io.kotest:kotest-assertions-core:5.5.5")
                //implementation("io.kotest:kotest-assertions-json:5.5.5")
                //implementation("com.squareup.okio:okio:3.3.0")
                implementation("com.squareup.okio:okio-fakefilesystem:$okioVersion")
            }
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
        val androidMain by getting
//        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val jsMain by getting
        val jsTest by getting
    }
}

dependencies {
    add("kspCommonMainMetadata", "at.kopyk:kopykat-ksp:1.0.1")
    add("kspJs", "at.kopyk:kopykat-ksp:1.0.1")
    add("kspAndroid", "at.kopyk:kopykat-ksp:1.0.1")
    add("kspIosX64", "at.kopyk:kopykat-ksp:1.0.1")
    add("kspIosArm64", "at.kopyk:kopykat-ksp:1.0.1")
    //ksp("at.kopyk:kopykat-ksp:1.0.1")
}

ksp {
    arg("generate", "packages:person")
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}