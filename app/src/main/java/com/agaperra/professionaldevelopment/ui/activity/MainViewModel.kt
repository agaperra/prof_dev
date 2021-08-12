package com.agaperra.professionaldevelopment.ui.activity

import com.agaperra.professionaldevelopment.BuildConfig
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.core.viewmodel.BaseViewModel
import com.agaperra.core.DictionaryInteractor
import kotlinx.coroutines.Dispatchers

class MainViewModel (
    private val interactor: com.agaperra.core.DictionaryInteractor<AppState>,
) : com.agaperra.core.viewmodel.BaseViewModel<AppState>() {

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