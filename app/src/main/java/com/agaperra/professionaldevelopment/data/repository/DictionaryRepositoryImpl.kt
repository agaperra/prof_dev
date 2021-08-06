package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.datasource.LocalData
import com.agaperra.professionaldevelopment.data.datasource.RemoteData

class DictionaryRepositoryImpl (
    private val remoteDatasource: RemoteData,
    private val localDataSource: LocalData
) :
    DictionaryRepository {

    override  suspend fun getWord(key:String, languageCode: String, query: String) =
        remoteDatasource.getData(key, languageCode, query)

    override  suspend fun fetchWord(word: Word, meanings: List<Meaning>)=
         localDataSource.fetchData(word, meanings)


    override suspend fun getWord(word: String) = localDataSource.getData(word)
}