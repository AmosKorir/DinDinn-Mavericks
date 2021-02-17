package com.food.data.di

import com.food.data.BuildConfig
import com.food.data.api.FoodApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        private fun provideHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original: Request = chain.request()
                        val request: Request = original.newBuilder()
                            .build()
                        return chain.proceed(request)
                    }
                })
                .addInterceptor(httpLoggingInterceptor)

            return okHttpClientBuilder.build()
        }

        private fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }

        private fun provideGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }

        private fun provideFoodApi(retrofit: Retrofit): FoodApi {
            return retrofit.create(FoodApi::class.java)
        }

        fun getFoodApi(): FoodApi {
            return provideFoodApi(provideRetrofit(provideGson(), provideHttpClient()))
        }

    }
}

