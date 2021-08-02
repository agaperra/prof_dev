package com.agaperra.professionaldevelopment.lesson_2.model.repository


import com.agaperra.professionaldevelopment.lesson_2.datasource.DataSource
import com.agaperra.professionaldevelopment.lesson_2.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

class MainRepository(private val dataSource: DataSource<List<DataModel>>) :
    DictionaryRepository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }

}