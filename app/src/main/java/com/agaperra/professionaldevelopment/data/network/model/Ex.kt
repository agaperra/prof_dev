package com.agaperra.professionaldevelopment.data.network.model

import com.google.gson.annotations.SerializedName

data class Ex (

    @SerializedName("text") val text : String,
    @SerializedName("tr") val tr : List<Tr>
)