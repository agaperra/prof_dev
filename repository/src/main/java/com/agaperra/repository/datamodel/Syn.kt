package com.agaperra.repository.datamodel

import com.google.gson.annotations.SerializedName

data class Syn (

    @SerializedName("text") val text : String,
    @SerializedName("pos") val pos : String,
    @SerializedName("gen") val gen : String,
    @SerializedName("fr") val fr : Int
)