package id.android.codebase.di

import id.android.codebase.features.home.HomeViewModel
import id.android.codebase.features.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel() }
}
