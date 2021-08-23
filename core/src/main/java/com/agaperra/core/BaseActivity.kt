package com.agaperra.core

import androidx.appcompat.app.AppCompatActivity
import com.agaperra.repository.state.AppState

abstract class BaseActivity<T : AppState, I : DictionaryInteractor<T>> : AppCompatActivity() {

    abstract fun renderData(dataModel: T)
    protected abstract val layoutRes: Int

}