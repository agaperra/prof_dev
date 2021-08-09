package com.agaperra.professionaldevelopment.data.network.api

import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(value = "/api/v1/dicservice.json/lookup")
    fun getWord(
        @Query("key") key :String,
        @Query("lang") language: String,
        @Query("text") word: String
    ) : Single<DictionaryResponse>

}