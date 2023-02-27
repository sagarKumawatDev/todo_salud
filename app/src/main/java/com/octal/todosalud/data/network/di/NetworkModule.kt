package com.octal.todosalud.data.network.di


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.octal.todosalud.BuildConfig
import com.octal.todosalud.data.network.TodoApiServices
import com.octal.todosalud.data.network.connectionManager.ConnectivityObserver
import com.octal.todosalud.data.network.connectionManager.ConnectivityObserverImpl
import com.octal.todosalud.data.network.response.NetworkResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @Named("baseURl") baseURL: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(NetworkResponseCallAdapterFactory)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("chuckerInterceptor")
        chuckerInterceptor: Interceptor,
        @Named("httpLoggingInterceptor")
        httpLoggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient().newBuilder().apply {
        addInterceptor(chuckerInterceptor)
        addInterceptor(httpLoggingInterceptor)
    }.build()

    @Singleton
    @Provides
    @Named("chuckerInterceptor")
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor =
        ChuckerInterceptor.Builder(context).build()

    @Singleton
    @Provides
    @Named("httpLoggingInterceptor")
    fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideTestApi(retrofit: Retrofit): TodoApiServices =
        retrofit.create(TodoApiServices::class.java)

    @Singleton
    @Provides
    @Named("baseURl")
    fun provideApiBaseURL(): String = "https://catfact.ninja/"

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        ConnectivityObserverImpl(context)
}
