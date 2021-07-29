package com.agaperra.professionaldevelopment.lesson_1.interactor

import com.agaperra.professionaldevelopment.lesson_1.AppState
import com.agaperra.professionaldevelopment.lesson_1.model.data.DataModel
import com.agaperra.professionaldevelopment.lesson_1.repository.DictionaryRepository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: DictionaryRepository<List<DataModel>>,
    private val localRepository: DictionaryRepository<List<DataModel>>
) : DictionaryInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}