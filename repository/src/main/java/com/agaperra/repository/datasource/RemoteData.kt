package com.agaperra.repository.datasource

import com.agaperra.repository.datamodel.DictionaryResponse

interface RemoteData {
    suspend fun getData(key:String, languageCode: String, query: String): DictionaryResponse
}