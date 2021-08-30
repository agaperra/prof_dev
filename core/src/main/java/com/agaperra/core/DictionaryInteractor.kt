package com.agaperra.core

import com.agaperra.repository.state.AppState


interface DictionaryInteractor <T> {

    suspend fun getWord(key:String, word: String, languageCode: String): AppState

}
