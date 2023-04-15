package com.antonio.datosperu.domain

import com.google.gson.annotations.SerializedName

class Person (
    @SerializedName("numero") val dni: String,
    @SerializedName("nombre_completo") val fullName: String,
    @SerializedName("nombres") val names: String,
    @SerializedName("apellido_paterno") val paternalSurname: String,
    @SerializedName("apellido_materno") val maternalSurname: String,
    @SerializedName("ubigeo_sunat") val ubigeo_sunat: String,
    @SerializedName("ubigeo") val ubigeo: List<String>
)


