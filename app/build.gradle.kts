plugins {
    id("com.android.application")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.android.inputmethod.latin"
        // applicationIdSuffix ".apk"
        minSdkVersion(15)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }
    
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFile("proguard-android.txt")
        }
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("../LatinIME/java/AndroidManifest.xml")
            java.setSrcDirs(listOf("../LatinIME/java/src", "../LatinIME/common/src", "../LatinIME/java-overridable/src", "../inputmethodcommon/java"))
            res.setSrcDirs(listOf("../LatinIME/java/res", "../LatinIME/java-overridable/res"))
        }
    }

    externalNativeBuild {
        ndkBuild {
            setPath("../LatinIME/native/jni/Android.mk")
        }
    }

    lintOptions {
        isAbortOnError = false
        isCheckReleaseBuilds = false
    }

    defaultConfig {
        externalNativeBuild {
            ndkBuild {
                // Suppress build failing warnings from dependencies
                cppFlags.add("-w")
                arguments.add("TARGET_BUILD_APPS=true")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.android.support:support-v4:28.0.0")
}
