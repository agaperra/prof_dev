package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.Word_Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.datasource.LocalData
import com.agaperra.professionaldevelopment.data.datasource.RemoteData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteData,
    private val localDataSource: LocalData
) :
    DictionaryRepository {

    override fun getWord(key:String, languageCode: String, query: String) =
        remoteDatasource.getData(key, languageCode, query)

    override fun fetchWord(word: Word, meanings: List<Meaning>): Single<Word_Meaning> {
        println(meanings)
        return localDataSource.fetchData(word, meanings)
    }

    override fun getWord(word: String) = localDataSource.getData(word)
}