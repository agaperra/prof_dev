package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.database.DictionaryDatabase
import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word

class DataSourceLocal(
    db: DictionaryDatabase
) : LocalData {

    private val wordsDao = db.dictionaryDao()

    override suspend fun fetchData(word: Word, meanings: List<Meaning>): Word_Meaning {
        wordsDao.insertWord(word = word)
        wordsDao.insertMeanings(meanings)
        return wordsDao.getWordWithMeanings(word.word)
    }


    override suspend fun getData(word: String): Word_Meaning =
        wordsDao.getWordWithMeanings(word)

}