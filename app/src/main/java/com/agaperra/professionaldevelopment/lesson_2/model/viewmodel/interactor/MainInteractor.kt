package com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.interactor


import com.agaperra.professionaldevelopment.lesson_2.AppState
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_LOCAL
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_REMOTE
import com.agaperra.professionaldevelopment.lesson_2.model.data.DataModel
import com.agaperra.professionaldevelopment.lesson_2.model.repository.DictionaryRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named


class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: DictionaryRepository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: DictionaryRepository<List<DataModel>>
) : DictionaryInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}
