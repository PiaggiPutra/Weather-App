package id.android.codebase.di

import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.ExampleRepository
import id.android.codebase.data.repository.ExampleRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<ExampleRepository> { ExampleRepositoryImpl(get(), get()) }
}
