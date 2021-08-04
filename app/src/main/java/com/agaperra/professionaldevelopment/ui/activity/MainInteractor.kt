package com.agaperra.professionaldevelopment.ui.activity


import androidx.room.rxjava3.EmptyResultSetException
import com.agaperra.professionaldevelopment.data.repository.Converter
import com.agaperra.professionaldevelopment.data.repository.DictionaryRepository
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: DictionaryRepository,
    private val localRepository: DictionaryRepository,
) : DictionaryInteractor<AppState> {

    override fun getWord(key:String, word: String, languageCode: String): Single<out AppState> =

            localRepository.getWord(word).map {
                AppState.Success(it)
            }
                .onErrorResumeNext { error ->
                    if (error is EmptyResultSetException) {
                        remoteRepository.getWord(key, languageCode, word).flatMap { response ->
                            println(response.def[0].tr[0].text)
                            localRepository.fetchWord(
                                Converter.convertToWord(response.def[0].text),
                                Converter.convertToMeanings(response)
                            ).map {
                                AppState.Success(it)
                            }
                        }
                    } else {
                        Single.error(error)
                    }
                }

}