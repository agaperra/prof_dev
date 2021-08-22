package com.agaperra.professionaldevelopment.koin

import android.content.Context
import androidx.room.Room
import com.agaperra.professionaldevelopment.BuildConfig
import com.agaperra.professionaldevelopment.ui.activity.MainActivity
import com.agaperra.professionaldevelopment.ui.activity.MainInteractor
import com.agaperra.professionaldevelopment.ui.activity.MainViewModel
import com.agaperra.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        listOf(
            mainView,
            root,
            localData,
            api
        )
    )
}


val api = module {
    single(named("BASE_URL")) { Constants.DICTIONARY_BASE_URL }
    factory { provideOkHttpClient() }
    single { provideRetrofit(baseUrl = get(qualifier = named("BASE_URL")), okHttpClient = get()) }
    factory { provideDictionaryApi(retrofit = get()) }
}

val localData = module {
    single { provideDictionaryDatabase(context = get()) }
}

val root = module {
    single<com.agaperra.repository.datasource.RemoteData> {
            com.agaperra.repository.datasource.DataSourceRemote(
                apiService = get()
            )
    }

    single<com.agaperra.repository.datasource.LocalData> {
            com.agaperra.repository.datasource.DataSourceLocal(
                db = get()
            )
    }

    single<com.agaperra.repository.repository.DictionaryRepository> {
            com.agaperra.repository.repository.DictionaryRepositoryImpl(
                remoteDatasource = get(),
                localDataSource = get()
            )
    }

}

val mainView = module {
    scope<MainActivity>{
        scoped { MainInteractor(localRepository = get(), remoteRepository = get()) }
        viewModel { MainViewModel(interactor =  get()) }
    }
}


fun provideDictionaryDatabase(context: Context): com.agaperra.repository.database.DictionaryDatabase =
    Room
        .databaseBuilder(
            context,
            com.agaperra.repository.database.DictionaryDatabase::class.java,
            "dictionary_database"
        )
        .fallbackToDestructiveMigration()
        .build()

fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
} else {
    OkHttpClient.Builder().build()
}

fun provideDictionaryApi(retrofit: Retrofit): com.agaperra.repository.api.ApiService =
    retrofit.create(
        com.agaperra.repository.api.ApiService::class.java
    )

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()