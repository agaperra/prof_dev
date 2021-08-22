package com.agaperra.professionaldevelopment.ui.activity

import androidx.lifecycle.viewModelScope
import com.agaperra.core.BaseViewModel
import com.agaperra.core.DictionaryInteractor
import com.agaperra.repository.state.AppState
import com.agaperra.professionaldevelopment.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val interactor: DictionaryInteractor<AppState>,
) : BaseViewModel<AppState>() {

    private var languageCode: String = "en-ru"

    override fun getData(word: String){
        liveDataForViewToObserve.value = AppState.Loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(
                interactor.getWord(BuildConfig.key, word, languageCode
                )
            )
        }
    }

    fun subscribe() = liveDataForViewToObserve


}