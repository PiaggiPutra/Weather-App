plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    /** Kotlin Dependencies */
    implementation(KotlinDependencies.kotlin)

    /** Koin Dependencies */
    implementation(KoinDependencies.koin)
    implementation(KoinDependencies.koinViewodel)

    /** Networking Dependencies */
    implementation(NetworkingDependencies.gson)
    implementation(NetworkingDependencies.retrofit)
    implementation(NetworkingDependencies.converterGson)
    implementation(NetworkingDependencies.loggingInterceptor)
    implementation(NetworkingDependencies.okhttp)

    /** Data Module */
    implementation(project(Modules.model))

    /** Testing Dependencies */
    testImplementation(TestingDependencies.junit)

    /** Chucker Dependencies */
    debugImplementation(ChuckerDependencies.chucker)
    releaseImplementation(ChuckerDependencies.chuckerNoOp)
}
