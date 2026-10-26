package com.cheterz.myshelf.di

import com.cheterz.myshelf.data.remote.MyShelfApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
object TestNetworkModule {
    var baseUrl: String = "http://localhost:8080/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyShelfApi(retrofit: Retrofit): MyShelfApi {
        return retrofit.create(MyShelfApi::class.java)
    }
}