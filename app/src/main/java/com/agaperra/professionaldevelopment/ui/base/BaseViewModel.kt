package com.agaperra.professionaldevelopment.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.rx.ISchedulerProvider
import com.agaperra.professionaldevelopment.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulers: ISchedulerProvider = SchedulerProvider
) : ViewModel() {

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() = compositeDisposable.dispose()

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    abstract fun handleError(error: Throwable)

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}