package com.agaperra.professionaldevelopment.ui.interactor

import com.agaperra.professionaldevelopment.data.state.AppState


interface DictionaryInteractor <T> {

    suspend fun getWord(key:String, word: String, languageCode: String): AppState

}
