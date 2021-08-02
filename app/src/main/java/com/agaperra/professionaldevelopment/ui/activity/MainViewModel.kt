package com.agaperra.professionaldevelopment.ui.activity

import androidx.lifecycle.LiveData
import com.agaperra.professionaldevelopment.BuildConfig
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.base.BaseViewModel
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: DictionaryInteractor<AppState>,
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null
    private var languageCode: String = "en-ru"

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable += interactor.getWord(BuildConfig.key, word, languageCode)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe {
                liveDataForViewToObserve.value = AppState.Loading(null)}
            .subscribeBy(
                onSuccess = {
                    appState = it
                    liveDataForViewToObserve.value = appState
                },
                onError = {
                    liveDataForViewToObserve.value = AppState.Error(it) }
            )
        return super.getData(word)
    }

    fun subscribe() = liveDataForViewToObserve


}