package com.agaperra.professionaldevelopment.di.module

import android.content.Context
import androidx.room.Room
import com.agaperra.professionaldevelopment.data.database.DictionaryDatabase
import com.agaperra.professionaldevelopment.data.datasource.DataSourceLocal
import com.agaperra.professionaldevelopment.data.datasource.DataSourceRemote
import com.agaperra.professionaldevelopment.data.datasource.LocalData
import com.agaperra.professionaldevelopment.data.datasource.RemoteData
import com.agaperra.professionaldevelopment.data.network.api.ApiService
import com.agaperra.professionaldevelopment.data.repository.DictionaryRepository
import com.agaperra.professionaldevelopment.data.repository.DictionaryRepositoryImpl
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.activity.MainInteractor
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDictionaryDatabases(context: Context): DictionaryDatabase = Room
        .databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRemoteDatasource(
        apiService: ApiService
    ): RemoteData = DataSourceRemote(apiService)

    @Singleton
    @Provides
    fun provideLocalDatasource(
        db: DictionaryDatabase
    ): LocalData = DataSourceLocal(db)

    @Singleton
    @Provides
    fun provideDictionaryRepository(
        remoteDataSource: RemoteData,
        localDataSource: LocalData
    ): DictionaryRepository = DictionaryRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    fun provideInteractor(
        remoteRepository: DictionaryRepository,
        localRepository: DictionaryRepository,
    ): DictionaryInteractor<AppState> = MainInteractor(remoteRepository, localRepository)

}