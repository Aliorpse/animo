plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

group = "tech.aliorpse.animo"

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.contentNegotiation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
