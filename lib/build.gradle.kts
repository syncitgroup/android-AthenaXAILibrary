@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "xai.athena.lib"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androix.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    // Chucker
    //debugImplementation(libs.chucker.debug)
    //releaseImplementation(libs.chucker.release)

    //ml-kit selfie segmentation
    implementation(libs.mlkit.selfie.segmentation)

    //kotlin coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.syncitgroup"
                artifactId = "android-AthenaXAILibrary"
                version = "1.0"
            }
        }
    }
}