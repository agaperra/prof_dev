package com.agaperra.professionaldevelopment.lesson_1.datasource

import com.agaperra.professionaldevelopment.lesson_1.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


class RoomDatabaseImpl : DictionaryDataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}