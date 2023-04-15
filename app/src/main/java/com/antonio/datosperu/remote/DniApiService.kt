package com.antonio.datosperu.remote

import com.antonio.datosperu.domain.DniResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface DniApiService {

    @GET("dni/{numero}")
    fun getDni(@Path("numero") numero: String): Call<DniResponse>
}