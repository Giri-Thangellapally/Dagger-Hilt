package com.example.practice_mvvm.di

import android.app.Application
import com.example.practice_mvvm.di.Modules.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 *@Author By Giri Thangellapally on 31-07-2021.
 */


class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(listOf(
                UserviewModelModule,
                LoginviewModelModule,
                userListRepositoryModule,
                apiModule,
                retrofitModule)
            )
        }
    }
}