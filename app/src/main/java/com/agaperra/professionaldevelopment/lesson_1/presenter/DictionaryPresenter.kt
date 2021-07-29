package com.agaperra.professionaldevelopment.lesson_1.presenter

import com.agaperra.professionaldevelopment.lesson_1.AppState
import com.agaperra.professionaldevelopment.lesson_1.view.DictionaryView

interface DictionaryPresenter <T : AppState, V : DictionaryView> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)

}
