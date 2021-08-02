package com.agaperra.professionaldevelopment.ui.interactor

import io.reactivex.rxjava3.core.Single


interface DictionaryInteractor <T> {

    fun getWord(word: String, languageCode: String): Single<out T>

}
