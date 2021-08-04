package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse
import io.reactivex.rxjava3.core.Single


interface DictionaryRepository {

    suspend fun getWord(key: String, languageCode: String, query: String): Single<DictionaryResponse>
    suspend fun getWord(word: String): Single<Word_Meaning>
    suspend fun fetchWord(word: Word, meanings: List<Meaning>): Single<Word_Meaning>
}
