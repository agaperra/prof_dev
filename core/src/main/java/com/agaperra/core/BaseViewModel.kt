package com.agaperra.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agaperra.professionaldevelopment.data.state.AppState

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    abstract fun getData(word: String)
}