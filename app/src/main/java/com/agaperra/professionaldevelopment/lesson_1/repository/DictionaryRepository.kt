package com.agaperra.professionaldevelopment.lesson_1.repository

import io.reactivex.Observable

interface DictionaryRepository <T> {

    fun getData(word: String): Observable<T>

}
