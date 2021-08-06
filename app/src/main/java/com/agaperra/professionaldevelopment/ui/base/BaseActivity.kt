package com.agaperra.professionaldevelopment.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor

abstract class BaseActivity<T : AppState, I : DictionaryInteractor<T>> : AppCompatActivity() {

    abstract fun renderData(dataModel: T)

}