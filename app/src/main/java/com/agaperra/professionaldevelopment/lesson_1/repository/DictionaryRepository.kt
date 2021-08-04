package com.agaperra.professionaldevelopment.lesson_1.repository

import io.reactivex.rxjava3.core.Observable


interface DictionaryRepository <T> {

    fun getData(word: String): Observable<T>

}
