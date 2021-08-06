package com.agaperra.professionaldevelopment.ui.activity


import com.agaperra.professionaldevelopment.data.repository.Converter
import com.agaperra.professionaldevelopment.data.repository.DictionaryRepository
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor

class MainInteractor (
    private val remoteRepository: DictionaryRepository,
    private val localRepository: DictionaryRepository,
) : DictionaryInteractor<AppState> {

    override suspend fun getWord(key: String, word: String, languageCode: String): AppState {
        var data = localRepository.getWord(word = word)
        if (data == null) {
            return try {
                val response = remoteRepository.getWord(key, languageCode, word)
                data = localRepository.fetchWord(
                    Converter.convertToWord(response.def[0].text),
                    Converter.convertToMeanings(response)
                )
                AppState.Success(data)
            } catch (e: Exception) {
                AppState.Error(e)
            }
        } else {
            return AppState.Success(data)
        }
    }
}