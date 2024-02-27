package id.android.codebase.di

import id.android.codebase.BuildConfig
import id.android.codebase.domain.di.domainModule

val appComponent = listOf(createRemoteModule(BuildConfig.BASE_URL), repositoryModule, localModule, featuresModule, domainModule)
