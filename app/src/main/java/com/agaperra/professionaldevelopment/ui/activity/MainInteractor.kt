package com.agaperra.professionaldevelopment.ui.activity


import com.agaperra.repository.state.AppState

class MainInteractor (
    private val remoteRepository: com.agaperra.repository.repository.DictionaryRepository,
    private val localRepository: com.agaperra.repository.repository.DictionaryRepository,
) : com.agaperra.core.DictionaryInteractor<AppState> {

    override suspend fun getWord(key: String, word: String, languageCode: String): AppState {
        var data = localRepository.getWord(word = word)
        if (data == null) {
            return try {
                val response = remoteRepository.getWord(key, languageCode, word)
                data = localRepository.fetchWord(
                    com.agaperra.repository.repository.Converter.convertToWord(response.def[0].text, response.def[0].ts, response.def[0].tr[0].text),
                    com.agaperra.repository.repository.Converter.convertToMeanings(response)
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