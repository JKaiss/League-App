package com.jaafoura.leagueapp.di.module

import com.jaafoura.leagueapp.common.utils.BASE_URL
import com.jaafoura.leagueapp.network.GlobalApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Module which provides all required dependencies about network
 */
@Module
@Suppress("unused")
object RemoteModule {

    /**
     * Provides the Global service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Global service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGlobalApi(retrofit: Retrofit): GlobalApi {
        return retrofit.create(GlobalApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(makeOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
                HttpLoggingInterceptor.Level.BODY
        return logging
    }
}