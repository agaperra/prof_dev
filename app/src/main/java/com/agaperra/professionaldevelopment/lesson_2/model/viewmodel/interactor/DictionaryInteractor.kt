package com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.interactor

import io.reactivex.rxjava3.core.Observable


interface DictionaryInteractor <T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>

}
