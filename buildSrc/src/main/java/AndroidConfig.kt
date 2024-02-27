object AndroidConfig {
    const val COMPILE_SDK_VERSION = 30
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.3"

    const val FLAVOR_DIMENSIONS = "default"

    const val ID = "id.android.codebase"
}

object Releases {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    const val isShrinkResources = true
}

object ProductFlavor {
    const val DEVELOPMENT = "development"
    const val PRODUCTION = "production"
}
