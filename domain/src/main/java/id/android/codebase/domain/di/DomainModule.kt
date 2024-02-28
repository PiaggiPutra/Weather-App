package id.android.codebase.domain.di

import id.android.codebase.domain.usecase.WeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { WeatherUseCase(get()) }
}