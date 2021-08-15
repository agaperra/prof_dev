package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse


interface DictionaryRepository {

    suspend fun getWord(key: String, languageCode: String, query: String): DictionaryResponse
    suspend fun getWord(word: String): Word_Meaning
    suspend fun fetchWord(word: Word, meanings: List<Meaning>): Word_Meaning
}
