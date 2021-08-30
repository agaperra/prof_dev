package com.agaperra.repository.repository

import com.agaperra.repository.database.Word_Meaning
import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.database.entity.Word
import com.agaperra.repository.datamodel.DictionaryResponse


interface DictionaryRepository {

    suspend fun getWord(key: String, languageCode: String, query: String): DictionaryResponse
    suspend fun getWord(word: String): Word_Meaning
    suspend fun fetchWord(word: Word, meanings: List<Meaning>): Word_Meaning
}
