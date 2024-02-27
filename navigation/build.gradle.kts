plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.NAVIGATION_SAFE_ARGS)
}

apply(from = "${project.rootDir}/android_commons.gradle")

dependencies {
    implementation(NavigationDependencies.navigationUi)
    implementation(NavigationDependencies.navigationFragment)
}
