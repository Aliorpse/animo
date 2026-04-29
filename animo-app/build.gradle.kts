import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hotReload)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":backend"))

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.lucide.icons)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.ktor.client.cio)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "tech.aliorpse.animo.AnimoAppKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "tech.aliorpse.animo"
            packageVersion = "1.0.0"
        }

        buildTypes.release.proguard {
            configurationFiles.from(project.file("proguard-rules.pro"))
        }
    }
}
