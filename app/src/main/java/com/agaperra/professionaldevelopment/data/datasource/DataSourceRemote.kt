package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.network.api.ApiService
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse

class DataSourceRemote(
    private val apiService: ApiService
) : RemoteData {

    override suspend fun getData(key:String, languageCode: String, query: String): DictionaryResponse =
        apiService.getWord(key, languageCode, query)
}