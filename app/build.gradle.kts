plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.hieunn.learnenglish"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.hieunn.learnenglish"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // YouTube Player đã xóa do lỗi Crash WebView
    // Card Stack View cho Flashcard (Load qua JitPack Github)
    implementation("com.github.yuyakaido:CardStackView:2.3.4")

    // Glide để load ảnh Thumbnail YouTube
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Room Database
    implementation("androidx.room:room-runtime:2.7.0-beta01")
    annotationProcessor("androidx.room:room-compiler:2.7.0-beta01")
}