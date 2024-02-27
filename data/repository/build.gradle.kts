plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    /** Modules */
    implementation(project(Modules.remote))
    implementation(project(Modules.local))
    api(project(Modules.model))

    /** Coroutines Dependencies */
    implementation(CoroutinesDependencies.coroutinesCore)
    implementation(CoroutinesDependencies.coroutinesAndroid)

    /** Koin Dependencies */
    implementation(KoinDependencies.koin)
    implementation(KoinDependencies.koinViewodel)

    /** Networking Dependencies */
    implementation(NetworkingDependencies.retrofit)

    /** Testing Dependencies */
    testImplementation(TestingDependencies.junit)
    testImplementation(TestingDependencies.mockk)
    testImplementation(TestingDependencies.coreTesting)
    testImplementation(TestingDependencies.coroutinesTest)
    testImplementation(project(Modules.commonTest))
}
