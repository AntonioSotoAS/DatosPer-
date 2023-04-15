package com.antonio.datosperu.domain

import com.google.gson.annotations.SerializedName

class DniResponse (
     @SerializedName("success")  val success: Boolean,
     @SerializedName("data") val data: Person,
     @SerializedName("source") val source: String,
)

