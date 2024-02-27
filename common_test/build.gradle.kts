plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    implementation(project(Modules.model))
    implementation(KoinDependencies.koin)
    implementation(KoinDependencies.koinViewodel)
    implementation(TestingDependencies.coroutinesTest)
    implementation(TestingDependencies.junit)
}
