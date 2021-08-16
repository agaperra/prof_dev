package com.agaperra.repository.datasource

import com.agaperra.repository.database.Word_Meaning
import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.database.entity.Word

interface LocalData  {
    suspend fun getData(word: String): Word_Meaning
    suspend fun fetchData(word: Word, meanings: List<Meaning>): Word_Meaning
}