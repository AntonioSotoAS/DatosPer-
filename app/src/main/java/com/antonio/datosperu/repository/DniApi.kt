package com.antonio.datosperu.repository

import com.antonio.datosperu.domain.DniResponse
import com.antonio.datosperu.remote.DniApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DniApi {

    private val token = "7073a06c2758760ee7e112280bee8c69b8aa7b4d2438f6fa49d5b48aa00753ec"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://apiperu.dev/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createOkHttpClient(token))
        .build()




    private val reniecApiService = retrofit.create(DniApiService::class.java)


    fun getDni(numeroDni: String): Call<DniResponse> {
        return reniecApiService.getDni(numeroDni)
    }

    private fun createOkHttpClient(token: String): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        return builder.build()
    }
}