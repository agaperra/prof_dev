package com.agaperra.professionaldevelopment.lesson_2.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agaperra.professionaldevelopment.lesson_2.AppState
import com.agaperra.professionaldevelopment.lesson_2.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}