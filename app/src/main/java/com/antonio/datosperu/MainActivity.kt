package com.antonio.datosperu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.antonio.datosperu.domain.DniResponse
import com.antonio.datosperu.repository.DniApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    private lateinit var TextViewDni: TextView
    private lateinit var TextViewNombre: TextView
    private lateinit var TextViewApellidoPaterno: TextView
    private lateinit var TextViewApellidoMaterno: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dni_details)

        val button = findViewById<Button>(R.id.verInfo)
        TextViewDni = findViewById<TextView>(R.id.dni)
        TextViewNombre = findViewById<TextView>(R.id.nombre)
        TextViewApellidoPaterno = findViewById<TextView>(R.id.apellido_paterno)
        TextViewApellidoMaterno = findViewById<TextView>(R.id.apellido_materno)

        val dni = findViewById<TextView>(R.id.editTextNumber)

        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val dniApi = DniApi()
                    val call = dniApi.getDni(dni.text.toString())
                    val response = call.awaitResponse()
                    if (response.isSuccessful) {
                        val dniResponse = response.body()
                        TextViewDni.text = dniResponse?.data?.dni
                        TextViewNombre.text = dniResponse?.data?.names
                        TextViewApellidoPaterno.text = dniResponse?.data?.maternalSurname
                        TextViewApellidoMaterno.text = dniResponse?.data?.paternalSurname

                    } else {
                        println("Error al obtener el DNI: ${response.code()} - ${response.message()}")
                    }
                } catch (e: IOException) {
                    println("No se pudo establecer una conexión con el servidor")
                }
            }
        }
    }

    private suspend fun <T> Call<T>.awaitResponse(): Response<T> {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    continuation.resume(response)
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}
