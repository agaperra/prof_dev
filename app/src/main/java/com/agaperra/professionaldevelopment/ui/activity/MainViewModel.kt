package com.agaperra.professionaldevelopment.ui.activity

import androidx.lifecycle.LiveData
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.base.BaseViewModel
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor
import com.agaperra.professionaldevelopment.utils.Languages
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: DictionaryInteractor<AppState>,
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null
    private var languageCode: String = "en-ru"

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable += interactor.getWord(word, languageCode)
            .subscribeOn(schedulers.ui())
            .observeOn(schedulers.io())
            .doOnSubscribe { liveDataForViewToObserve.postValue( AppState.Loading(null))}
            .subscribeBy(
                onSuccess = {
                    appState = it
                    liveDataForViewToObserve.postValue(appState)
                },
                onError = { liveDataForViewToObserve.postValue(AppState.Error(it)) }
            )
        return super.getData(word)
    }

    fun subscribe() = liveDataForViewToObserve


}