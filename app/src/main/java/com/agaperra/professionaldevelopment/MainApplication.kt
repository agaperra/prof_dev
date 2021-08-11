package com.agaperra.professionaldevelopment

import android.app.Application
import com.agaperra.professionaldevelopment.koin.api
import com.agaperra.professionaldevelopment.koin.localData
import com.agaperra.professionaldevelopment.koin.mainView
import com.agaperra.professionaldevelopment.koin.root
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    mainView,
                    root,
                    localData,
                    api
                )
            )
        }
    }
}

