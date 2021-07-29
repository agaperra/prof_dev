package com.agaperra.professionaldevelopment.lesson_1.datasource

import com.agaperra.professionaldevelopment.lesson_1.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDatabaseImpl = RoomDatabaseImpl()) :
    DictionaryDataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}