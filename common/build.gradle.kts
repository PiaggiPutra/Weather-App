plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

android {

    flavorDimensions.add(AndroidConfig.FLAVOR_DIMENSIONS)

    productFlavors {
        create(ProductFlavor.DEVELOPMENT) {
            dimension = AndroidConfig.FLAVOR_DIMENSIONS
        }
        create(ProductFlavor.PRODUCTION) {
        }
    }

    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {
    implementation(AndroidXSupportDependencies.appcompat)
    implementation(AndroidXSupportDependencies.constraint)
    implementation(NavigationDependencies.navigationUi)
    implementation(NavigationDependencies.navigationFragment)
    implementation(project(Modules.navigation))
}
