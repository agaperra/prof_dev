package com.agaperra.repository.repository

import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.datasource.LocalData
import com.agaperra.repository.datasource.RemoteData

class DictionaryRepositoryImpl (
    private val remoteDatasource: RemoteData,
    private val localDataSource: LocalData
) :
    DictionaryRepository {

    override  suspend fun getWord(key:String, languageCode: String, query: String) =
        remoteDatasource.getData(key, languageCode, query)

    override  suspend fun fetchWord(word: com.agaperra.repository.database.entity.Word, meanings: List<Meaning>)=
         localDataSource.fetchData(word, meanings)


    override suspend fun getWord(word: String) = localDataSource.getData(word)
}