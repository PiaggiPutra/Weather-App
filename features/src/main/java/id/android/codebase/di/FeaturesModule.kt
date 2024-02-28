package id.android.codebase.di

import id.android.codebase.features.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { HomeViewModel(get(), get()) }
}
