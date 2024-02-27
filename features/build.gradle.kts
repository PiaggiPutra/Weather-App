plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_feature_dependencies.gradle")

android {

    flavorDimensions.add(AndroidConfig.FLAVOR_DIMENSIONS)

    productFlavors {
        create(ProductFlavor.DEVELOPMENT) {
            dimension = AndroidConfig.FLAVOR_DIMENSIONS
        }
        create(ProductFlavor.PRODUCTION) {
        }
    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.navigation))
    implementation(AndroidXSupportDependencies.legacySupport)

    /** Glide Dependencies */
    implementation(GlideDependencies.glide)
    implementation(GlideDependencies.okhttpIntegration)
    kapt(GlideDependencies.glideCompiler)

    /** Utils Dependencies */
    implementation(UtilsDependencies.sdp)
    implementation(UtilsDependencies.ssp)

}
