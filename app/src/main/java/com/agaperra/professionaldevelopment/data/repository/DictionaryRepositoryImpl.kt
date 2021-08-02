package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.datasource.LocalData
import com.agaperra.professionaldevelopment.data.datasource.RemoteData
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteData,
    private val localDataSource: LocalData
) :
    DictionaryRepository {

    override fun getWord(languageCode: String, query: String) =
        remoteDatasource.getData(languageCode, query)

    override fun fetchWord(word: Word, meanings: List<Meaning>) =
        localDataSource.fetchData(word, meanings)

    override fun getWord(word: String) = localDataSource.getData(word)
}