package com.agaperra.professionaldevelopment.lesson_1.interactor

import io.reactivex.rxjava3.core.Observable


interface DictionaryInteractor <T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>

}
