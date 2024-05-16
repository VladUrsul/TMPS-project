plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.project.remindtask.resources"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
}