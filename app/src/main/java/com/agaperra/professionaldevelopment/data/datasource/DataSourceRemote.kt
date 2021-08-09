package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.network.api.ApiService
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse
import com.agaperra.professionaldevelopment.data.state.AppState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.ArrayList
import javax.inject.Inject

class DataSourceRemote @Inject constructor(
    private val apiService: ApiService
) : RemoteData {

    override fun getData(key:String, languageCode: String, query: String): Single<DictionaryResponse> =
        apiService.getWord(key, languageCode, query)
}