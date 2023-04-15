package com.antonio.datosperu


import android.util.Log
import com.antonio.datosperu.repository.DniApi
import java.io.IOException


fun main() {
    // ya funciona
    try {
        val dniApi = DniApi()
        val call = dniApi.getDni("75098945")
        val response = call.execute()
        println("response: ${response.run {
            body()?.success.toString()
        }}")

        if (response.isSuccessful) {
            val dniResponse = response.body()
            println("dniResponse: ${dniResponse?.data.toString()}")
            println(dniResponse?.data?.dni)
            println(dniResponse?.data?.names)
            println(dniResponse?.data?.maternalSurname)
            println(dniResponse?.data?.paternalSurname)
        } else {
            println("Error al obtener el DNI: ${response.code()} - ${response.message()}")
        }
    } catch (e: IOException) {
        println("No se pudo establecer una conexión con el servidor")
    }
}



