package com.example.practice_mvvm.di.Modules

import com.example.practice_mvvm.Repositories.LoginRepository
import com.example.practice_mvvm.Repositories.UserListRepository
import com.example.practice_mvvm.network.ApiInterface
import com.example.practice_mvvm.network.WebConstants.Companion.BASE_URL
import com.example.practice_mvvm.viewModels.LoginViewModel
import com.example.practice_mvvm.viewModels.UsersListViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



// User ViewModel
val UserviewModelModule = module {
    single { UsersListViewModel(get()) }
}
//Login View Model
  val LoginviewModelModule = module {
      single { LoginViewModel(get ()) }
}

//Repository
val userListRepositoryModule= module {
    single { UserListRepository(get())}
    single {  LoginRepository(get())}
}


//api Module
val apiModule= module {
    fun provideUserApi(retrofit: Retrofit):ApiInterface{ return retrofit.create(ApiInterface::class.java) }
    single { provideUserApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }
    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}