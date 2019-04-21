package com.example.githubsample.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private var retrofit: Retrofit? = null
    const val BASE_URL = "https://github.com"
    const val API_BASE_URL = "https://api.github.com"
    const val TOKEN = "token "


    fun getRetrofitInstance(): Retrofit {

        return if (retrofit != null) {
            retrofit!!
        } else newRetrofit(BASE_URL)
    }

    private fun newRetrofit(url: String): Retrofit {

        val httpClient = getOkHttpClient(url)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit!!
    }

    fun getOkHttpClient(url: String): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return build()
            .addNetworkInterceptor(StethoInterceptor())
            //  .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).build()
    }

    fun build(): OkHttpClient.Builder {

        val okHttpBuilder = OkHttpClient.Builder()

        return okHttpBuilder
    }
}
