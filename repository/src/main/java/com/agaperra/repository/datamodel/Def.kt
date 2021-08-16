package com.agaperra.repository.datamodel

import com.google.gson.annotations.SerializedName

data class Def (

    @SerializedName("text") val text : String,
    @SerializedName("pos") val pos : String,
    @SerializedName("ts") val ts : String,
    @SerializedName("tr") val tr : List<Tr>
)