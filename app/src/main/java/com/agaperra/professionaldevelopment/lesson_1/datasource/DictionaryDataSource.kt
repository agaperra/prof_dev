package com.agaperra.professionaldevelopment.lesson_1.datasource

import io.reactivex.rxjava3.core.Observable


interface DictionaryDataSource <T> {

    fun getData(word: String): Observable<T>

}
