package com.agaperra.professionaldevelopment.lesson_1.repository

import com.agaperra.professionaldevelopment.lesson_1.datasource.DictionaryDataSource
import com.agaperra.professionaldevelopment.lesson_1.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

class MainRepository(private val dataSource: DictionaryDataSource<List<DataModel>>) :
    DictionaryRepository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }

}