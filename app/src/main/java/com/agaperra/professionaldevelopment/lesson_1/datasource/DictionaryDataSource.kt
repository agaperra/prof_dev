package com.agaperra.professionaldevelopment.lesson_1.datasource

import io.reactivex.Observable

interface DictionaryDataSource <T> {

    fun getData(word: String): Observable<T>

}
