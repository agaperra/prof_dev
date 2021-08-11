package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word

interface LocalData  {
    suspend fun getData(word: String): Word_Meaning
    suspend fun fetchData(word: Word, meanings: List<Meaning>): Word_Meaning
}