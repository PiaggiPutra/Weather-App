package id.android.codebase.di

import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.WeatherRepositoryImpl
import id.android.codebase.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
}
