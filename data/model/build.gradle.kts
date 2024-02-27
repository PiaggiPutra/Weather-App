plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    implementation(KotlinDependencies.kotlin)
    implementation(NetworkingDependencies.gson)
    implementation(RoomDependencies.roomRuntime)
    kapt(RoomDependencies.roomCompiler)
}
