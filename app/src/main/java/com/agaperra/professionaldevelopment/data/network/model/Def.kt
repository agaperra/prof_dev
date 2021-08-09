package com.agaperra.professionaldevelopment.data.network.model

import com.google.gson.annotations.SerializedName

data class Def (

    @SerializedName("text") val text : String,
    @SerializedName("pos") val pos : String,
    @SerializedName("ts") val ts : String,
    @SerializedName("tr") val tr : List<Tr>
)