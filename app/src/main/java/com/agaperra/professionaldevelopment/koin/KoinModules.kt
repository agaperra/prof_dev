package com.agaperra.professionaldevelopment.koin

import android.content.Context
import androidx.room.Room
import com.agaperra.core.DictionaryInteractor
import com.agaperra.professionaldevelopment.BuildConfig
import com.agaperra.professionaldevelopment.ui.activity.MainActivity
import com.agaperra.professionaldevelopment.ui.activity.MainInteractor
import com.agaperra.professionaldevelopment.ui.activity.MainViewModel
import com.agaperra.repository.api.ApiService
import com.agaperra.repository.database.DictionaryDatabase
import com.agaperra.repository.datasource.DataSourceLocal
import com.agaperra.repository.datasource.DataSourceRemote
import com.agaperra.repository.datasource.LocalData
import com.agaperra.repository.datasource.RemoteData
import com.agaperra.repository.repository.DictionaryRepository
import com.agaperra.repository.repository.DictionaryRepositoryImpl
import com.agaperra.repository.state.AppState
import com.agaperra.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        listOf(
            root,
            localData,
            api,
            mainView,
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
    single<RemoteData> {
            DataSourceRemote(
                apiService = get()
            )
    }

    single<LocalData> {
            DataSourceLocal(
                db = get()
            )
    }

    single<DictionaryRepository> {
            DictionaryRepositoryImpl(
                remoteDatasource = get(),
                localDataSource = get()
            )
    }

}

val mainView = module {
    scope<MainActivity>{
        scoped { MainInteractor(remoteRepository= get(), localRepository = get()) } bind DictionaryInteractor::class
        viewModel { MainViewModel(interactor = get())}
    }
}


fun provideDictionaryDatabase(context: Context): DictionaryDatabase =
    Room
        .databaseBuilder(
            context,
            DictionaryDatabase::class.java,
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

fun provideDictionaryApi(retrofit: Retrofit): ApiService =
    retrofit.create(
        ApiService::class.java
    )

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()