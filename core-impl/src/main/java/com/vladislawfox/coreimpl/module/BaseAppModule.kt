package com.vladislawfox.coreimpl.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by vladislawfox on 1/19/19.
 */
@Module
abstract class BaseAppModule {
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient)
            return retrofitBuilder.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create(Gson())
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            val interceptor1 = Interceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "")
                    .build()

                val request = original.newBuilder().url(url).build()
                chain.proceed(request)
            }
            val builder = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor1)
                .followRedirects(true)
            return builder.build()
        }
    }
}