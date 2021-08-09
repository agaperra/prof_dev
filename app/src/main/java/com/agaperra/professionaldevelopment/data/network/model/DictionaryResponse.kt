package com.agaperra.professionaldevelopment.data.network.model

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("def") val def : List<Def>
)