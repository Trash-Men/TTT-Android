package com.tjrwns8024.ttt_android.di.modules

import com.tjrwns8024.data.remote.Api
import com.tjrwns8024.data.remote.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Singleton
    @Provides
    fun provideApiService(
        authorizationInterceptor: AuthorizationInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): Api {
        return Retrofit.Builder()
            .baseUrl("http://dsm-rank.site/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authorizationInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}