package com.agaperra.core

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agaperra.repository.state.AppState
import com.agaperra.utils.OnlineLiveData

abstract class BaseActivity<T : AppState, I : DictionaryInteractor<T>> : AppCompatActivity() {

    abstract fun renderData(dataModel: T)
    protected abstract val layoutRes: Int

    protected var isNetworkAvailable: Boolean = true


}