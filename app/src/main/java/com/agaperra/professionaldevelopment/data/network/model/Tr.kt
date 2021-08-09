package com.agaperra.professionaldevelopment.data.network.model

import com.google.gson.annotations.SerializedName

data class Tr (

    @SerializedName("text") val text : String,
    @SerializedName("pos") val pos : String,
    @SerializedName("gen") val gen : String,
    @SerializedName("fr") val fr : Int,
    @SerializedName("syn") val syn : List<Syn>,
    @SerializedName("mean") val mean : List<Mean>,
    @SerializedName("ex") val ex : List<Ex>
)