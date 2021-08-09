package com.agaperra.professionaldevelopment.ui.interactor

import com.agaperra.professionaldevelopment.data.state.AppState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface DictionaryInteractor <T> {

    fun getWord(key:String, word: String, languageCode: String): Single<out AppState>

}
