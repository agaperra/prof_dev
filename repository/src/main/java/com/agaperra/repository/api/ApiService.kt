package com.agaperra.repository.api

import com.agaperra.repository.datamodel.DictionaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(value = "/api/v1/dicservice.json/lookup")
    suspend fun getWord(
        @Query("key") key :String,
        @Query("lang") language: String,
        @Query("text") word: String
    ) : DictionaryResponse

}