package id.android.codebase.di

import id.android.codebase.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE = "DATABASE"

val localModule = module {
    single(named(DATABASE)) { AppDatabase.buildDatabase(androidContext()) }
    factory { (get(named(DATABASE)) as AppDatabase).exampleDao() }
}
