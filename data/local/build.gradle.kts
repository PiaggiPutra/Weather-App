plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    /** Room Dependencies */
    kapt(RoomDependencies.sqliteJdbc)
    kapt(RoomDependencies.roomCompiler)
    implementation(RoomDependencies.roomRuntime)
    implementation(RoomDependencies.roomKtx)

    /** Testing Module */
    androidTestImplementation(project(Modules.commonTest))

    /** Data Module */
    implementation(project(Modules.model))

    /** Koin Dependencies */
    implementation(KoinDependencies.koin)
    implementation(KoinDependencies.koinViewodel)

    /** Testing Dependencies */
    androidTestImplementation(TestingDependencies.junit)
    androidTestImplementation(TestingDependencies.coreTesting)
}
