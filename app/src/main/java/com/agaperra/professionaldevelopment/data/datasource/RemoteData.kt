package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse

interface RemoteData {
    suspend fun getData(key:String, languageCode: String, query: String): DictionaryResponse
}