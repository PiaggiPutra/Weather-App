import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id(GradlePluginId.DETEKT).version(Versions.classpath_detekt)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = MavenUri.jitpack }
        maven { url = MavenUri.gradleM2 }
    }
    dependencies {
        classpath(ClasspathDependencies.gradle)
        classpath(ClasspathDependencies.kotlinGradlePlugin)
        classpath(ClasspathDependencies.navigationSafeArgs)
        classpath(ClasspathDependencies.detektGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = MavenUri.jitpack }
        maven { url = MavenUri.gradleM2 }
    }

    apply {
        plugin(GradlePluginId.DETEKT)
    }

    detekt {
        config = files("${rootProject.projectDir}/config/detekt/config.yml")
        source = files("src/main/java", "src/main/kotlin")
        baseline = file("${rootProject.projectDir}/config/detekt/baseline.xml")
        allRules = false
        buildUponDefaultConfig = true
        reports {
            html {
                enabled = true
                destination = file("${rootProject.projectDir}/build/reports/detekt/report.html")
            }
            txt {
                enabled = false
            }
            xml {
                enabled = false
            }
        }
    }
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
