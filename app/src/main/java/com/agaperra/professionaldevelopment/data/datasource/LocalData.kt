package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import io.reactivex.rxjava3.core.Single

interface LocalData  {
    fun getData(word: String): Single<Word_Meaning>
    fun fetchData(word: Word, meanings: List<Meaning>): Single<Word_Meaning>
}