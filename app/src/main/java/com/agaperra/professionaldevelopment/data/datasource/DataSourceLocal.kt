package com.agaperra.professionaldevelopment.data.datasource

import com.agaperra.professionaldevelopment.data.database.DictionaryDatabase
import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceLocal @Inject constructor(
    db: DictionaryDatabase
) : LocalData {

    private val wordsDao = db.dictionaryDao()

    override fun fetchData(word: Word, meanings: List<Meaning>): Single<Word_Meaning> {
        return wordsDao.insertWord(word = word)
            .andThen(wordsDao.insertMeanings(meanings))
            .andThen(wordsDao.getWordWithMeanings(word.word))
    }


    override fun getData(word: String): Single<Word_Meaning> =
        wordsDao.getWordWithMeanings(word)

}