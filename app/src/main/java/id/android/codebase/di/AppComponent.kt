package id.android.codebase.di

val appComponent = listOf(createRemoteModule("https://api.github.com/"), repositoryModule, localModule, featuresModule)
