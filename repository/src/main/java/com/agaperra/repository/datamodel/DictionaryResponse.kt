package com.agaperra.repository.datamodel

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("def") val def : List<Def>
)