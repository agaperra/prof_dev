package com.agaperra.professionaldevelopment.koin

import android.content.Context
import androidx.room.Room
import com.agaperra.professionaldevelopment.BuildConfig
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
import com.agaperra.professionaldevelopment.ui.activity.MainViewModel
import com.agaperra.professionaldevelopment.ui.interactor.DictionaryInteractor
import com.agaperra.professionaldevelopment.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val api = module {
    single(named("BASE_URL")) { Constants.DICTIONARY_BASE_URL }
    factory { provideOkHttpClient() }
    single { provideRetrofit(baseUrl = get(qualifier = named("BASE_URL")), okHttpClient = get()) }
    factory { provideDictionaryApi(retrofit = get()) }
}

val localDataModule = module {
    single { provideDictionaryDatabase(context = get()) }
}

val domainModule = module {
    single<RemoteData> { DataSourceRemote(apiService = get()) }
    single<LocalData> { DataSourceLocal(db = get()) }

    single<DictionaryRepository> {
        DictionaryRepositoryImpl(
            remoteDatasource = get(),
            localDataSource = get()
        )
    }
}

val presentationModule = module {
    single<DictionaryInteractor<AppState>> {
        MainInteractor(
            remoteRepository = get(),
            localRepository = get()
        )
    }
    viewModel { MainViewModel(interactor = get()) }
}

fun provideDictionaryDatabase(context: Context): DictionaryDatabase = Room
    .databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_database")
    .fallbackToDestructiveMigration()
    .build()

fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
} else {
    OkHttpClient.Builder().build()
}

fun provideDictionaryApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()