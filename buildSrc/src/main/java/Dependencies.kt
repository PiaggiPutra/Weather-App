import java.net.URI

object KotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidXSupportDependencies {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
}

object CoroutinesDependencies {
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object LifecycleDependencies {
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object NavigationDependencies {
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object KoinDependencies {
    const val koin = "io.insert-koin:koin-android:${Versions.classpath_koin}"
    const val koinViewodel = "io.insert-koin:koin-android-viewmodel:${Versions.classpath_koin}"
}

object RoomDependencies {
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val sqliteJdbc = "org.xerial:sqlite-jdbc:${Versions.sqliteJdbc}"
}

object NetworkingDependencies {
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}

object ChuckerDependencies {
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
}

object GlideDependencies {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val okhttpIntegration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
}

object UtilsDependencies {
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.sdpAndroid}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.sdpAndroid}"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.classpath_detekt}"
}

object ClasspathDependencies {
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.classpath_detekt}"
    const val gradle = "com.android.tools.build:gradle:${Versions.classpath_gradle}"
    const val koin = "io.insert-koin:koin-gradle-plugin:${Versions.classpath_koin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object TestingDependencies {
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}

object MavenUri {
    val gradleM2 = URI("https://plugins.gradle.org/m2/")
    val jitpack = URI("https://jitpack.io")
}
