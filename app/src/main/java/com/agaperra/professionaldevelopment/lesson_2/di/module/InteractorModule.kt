package com.agaperra.professionaldevelopment.lesson_2.di.module


import com.agaperra.professionaldevelopment.lesson_2.model.data.DataModel
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_LOCAL
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_REMOTE
import com.agaperra.professionaldevelopment.lesson_2.model.repository.DictionaryRepository
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.interactor.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: DictionaryRepository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: DictionaryRepository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}