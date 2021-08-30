package com.agaperra.repository.datasource

import com.agaperra.repository.database.entity.Meaning

class DataSourceLocal(
    db: com.agaperra.repository.database.DictionaryDatabase
) : LocalData {

    private val wordsDao = db.dictionaryDao()

    override suspend fun fetchData(word: com.agaperra.repository.database.entity.Word, meanings: List<Meaning>): com.agaperra.repository.database.Word_Meaning {
        wordsDao.insertWord(word = word)
        wordsDao.insertMeanings(meanings)
        return wordsDao.getWordWithMeanings(word.word)
    }


    override suspend fun getData(word: String): com.agaperra.repository.database.Word_Meaning =
        wordsDao.getWordWithMeanings(word)

}