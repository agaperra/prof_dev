package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse
import io.reactivex.rxjava3.core.Single

interface RemoteData {
    fun getData(languageCode: String, query: String): Single<DictionaryResponse>
}