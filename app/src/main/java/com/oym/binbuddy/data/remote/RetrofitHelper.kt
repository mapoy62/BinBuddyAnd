package com.oym.binbuddy.data.remote

import com.oym.binbuddy.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    // Configurar cliente OkHttp con interceptores
    private fun getClientWithApiKey(apiKey: String? = null): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val apiKeyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val newUrl = originalUrl.newBuilder()
                .apply {
                    apiKey?.let { addQueryParameter("api_key", it) } // Adjunta la API Key si aplica
                }
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    // Instancia de Retrofit para Apiary
    fun getApiaryRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getClientWithApiKey())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instancia de Retrofit para SerpAPI
    fun getSerpApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.SERPAPI_URL)
            .client(getClientWithApiKey(Constants.SERPAPI_API_KEY))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Instancia de Retrofit para Twiiter
    fun getTwitterRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val bearerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${Constants.TWITTER_API_KEY}")
                .build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(bearerInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.TWITTER_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
