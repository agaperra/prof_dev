package com.agaperra.professionaldevelopment.lesson_2.di.module

import com.agaperra.professionaldevelopment.lesson_2.datasource.DataSource
import com.agaperra.professionaldevelopment.lesson_2.datasource.RetrofitImplementation
import com.agaperra.professionaldevelopment.lesson_2.datasource.RoomDatabaseImpl
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_LOCAL
import com.agaperra.professionaldevelopment.lesson_2.di.NAME_REMOTE
import com.agaperra.professionaldevelopment.lesson_2.model.data.DataModel
import com.agaperra.professionaldevelopment.lesson_2.model.repository.DictionaryRepository
import com.agaperra.professionaldevelopment.lesson_2.model.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): MainRepository =
        MainRepository(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): DictionaryRepository<List<DataModel>> =
        MainRepository(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): RetrofitImplementation =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): RoomDatabaseImpl = RoomDatabaseImpl()
}
