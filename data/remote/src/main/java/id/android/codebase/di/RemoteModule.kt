package id.android.codebase.di

import id.android.codebase.data.remote.ExampleDatasource
import id.android.codebase.data.remote.ExampleService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.chuckerteam.chucker.api.ChuckerInterceptor.Builder as ChuckerBuilder

fun createRemoteModule(baseUrl: String) = module {

    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(ChuckerBuilder(androidApplication().applicationContext).build())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ExampleService::class.java) }

    factory { ExampleDatasource(get()) }
}
