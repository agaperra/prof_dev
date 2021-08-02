package com.agaperra.professionaldevelopment.lesson_2.model.repository

import io.reactivex.rxjava3.core.Observable


interface DictionaryRepository <T> {

    fun getData(word: String): Observable<T>

}
