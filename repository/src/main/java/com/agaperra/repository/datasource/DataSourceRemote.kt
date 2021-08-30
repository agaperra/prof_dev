package com.agaperra.repository.datasource

import com.agaperra.repository.datamodel.DictionaryResponse

class DataSourceRemote(
    private val apiService: com.agaperra.repository.api.ApiService
) : RemoteData {

    override suspend fun getData(key:String, languageCode: String, query: String): DictionaryResponse =
        apiService.getWord(key, languageCode, query)
}