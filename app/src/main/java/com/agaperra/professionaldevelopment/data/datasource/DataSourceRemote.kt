package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.network.api.ApiService
import javax.inject.Inject

class DataSourceRemote @Inject constructor(
    private val apiService: ApiService
) : RemoteData {

    override fun getData(languageCode: String, query: String) =
        apiService.getWord(languageCode, query)
}