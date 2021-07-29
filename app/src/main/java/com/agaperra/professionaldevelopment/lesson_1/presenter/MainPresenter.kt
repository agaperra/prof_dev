package com.agaperra.professionaldevelopment.lesson_1.presenter

import com.agaperra.professionaldevelopment.lesson_1.AppState
import com.agaperra.professionaldevelopment.lesson_1.datasource.DataSourceLocal
import com.agaperra.professionaldevelopment.lesson_1.datasource.DataSourceRemote
import com.agaperra.professionaldevelopment.lesson_1.interactor.MainInteractor
import com.agaperra.professionaldevelopment.lesson_1.repository.MainRepository
import com.agaperra.professionaldevelopment.lesson_1.rx.SchedulerProvider
import com.agaperra.professionaldevelopment.lesson_1.view.DictionaryView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenter<T : AppState, V : DictionaryView>(

    private val interactor: MainInteractor = MainInteractor(
        MainRepository(DataSourceRemote()),
        MainRepository(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : DictionaryPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}