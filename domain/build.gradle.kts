plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    /** Modules */
    api(project(Modules.model))

    /** Coroutines Dependencies */
    implementation(CoroutinesDependencies.coroutinesCore)
    implementation(CoroutinesDependencies.coroutinesAndroid)

    /** Koin Dependencies */
    implementation(KoinDependencies.koin)

    /** Testing Dependencies */
    testImplementation(TestingDependencies.junit)
    testImplementation(TestingDependencies.mockk)
    testImplementation(TestingDependencies.coreTesting)
    testImplementation(TestingDependencies.coroutinesTest)
    testImplementation(project(Modules.commonTest))
}